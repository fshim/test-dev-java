package com.github.fshim.testdevjava.service.impl;

import com.github.fshim.testdevjava.domain.TransactionNotification;
import com.github.fshim.testdevjava.event.NotificationEvent;
import com.github.fshim.testdevjava.service.ReportTemplate;
import org.springframework.stereotype.Service;

@Service
public class DefaultReport implements ReportTemplate<NotificationEvent> {

    @Override
    public NotificationEvent generateMessage(TransactionNotification event) {
        var message = switch (event.getNotificationType()) {
            case NOT_FOUND -> "Transaction not found";
            case INVALID_TRANSACTION -> "Invalid transaction (different amounts, missing data)";
            case VALID_TRANSACTION -> "Valid transaction";
        };

        return NotificationEvent.builder()
                .message(message)
                .data(event)
                .build();
    }
}