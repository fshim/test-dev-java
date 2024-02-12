package com.github.fshim.testdevjava.service;

import com.github.fshim.testdevjava.domain.TransactionData;
import com.github.fshim.testdevjava.domain.TransactionNotification;
import com.github.fshim.testdevjava.persistance.entity.TransactionsEntity;
import com.github.fshim.testdevjava.persistance.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.github.fshim.testdevjava.domain.NotificationType.INVALID_TRANSACTION;
import static com.github.fshim.testdevjava.domain.NotificationType.NOT_FOUND;
import static com.github.fshim.testdevjava.domain.NotificationType.VALID_TRANSACTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionEventService {

    private final TransactionsRepository transactionsRepository;
    private final List<NotificationService> notificationServices;

    @Transactional(readOnly = true)
    public void validate(TransactionData data) {
        transactionsRepository.findById(data.getId())
                .ifPresentOrElse(entity -> transactionFound(data, entity), () -> transactionNotFound(data));
    }

    private void transactionNotFound(TransactionData data) {
        log.warn("Transaction(id:{}) not found.", data.getId());
        var notification = TransactionNotification.builder()
                .notificationType(NOT_FOUND)
                .transactionId(data.getId())
                .build();

        notificationServices.forEach(notificationService -> notificationService.notify(notification));
    }

    private void transactionFound(TransactionData data, TransactionsEntity entity) {
        var notification = TransactionNotification.builder()
                .transactionId(data.getId())
                .actualAmount(data.getAmount())
                .expectedAmount(entity.getAmount())
                .build();

        if (Objects.isNull(entity.getAmount()) ||
                Objects.isNull(data.getAmount()) ||
                entity.getAmount().compareTo(data.getAmount()) != 0) {
            log.warn("Transaction(id:{}) have different amount. Actual: {}, expected: {}.",
                    data.getId(),
                    data.getAmount(),
                    entity.getAmount()
            );
            notification.setNotificationType(INVALID_TRANSACTION);
        } else {
            log.info("Transaction(id:{}) is valid.", data.getId());
            notification.setNotificationType(VALID_TRANSACTION);
        }

        notificationServices.forEach(notificationService -> notificationService.notify(notification));
    }
}