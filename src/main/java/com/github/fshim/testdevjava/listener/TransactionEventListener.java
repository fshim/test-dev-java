package com.github.fshim.testdevjava.listener;

import com.github.fshim.testdevjava.domain.TransactionData;
import com.github.fshim.testdevjava.event.TransactionEvent;
import com.github.fshim.testdevjava.service.TransactionEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionEventListener {

    private final TransactionEventService transactionEventService;

    @KafkaListener(
            topics = "${kafka.transaction-event.name}",
            containerFactory = "transactionKafkaListenerContainerFactory"
    )
    public void listen(TransactionEvent event, Acknowledgment ack) {
        log.info("Receive message: {}", event);
        transactionEventService.validate(new TransactionData(event.getId(), event.getAmount()));
        ack.acknowledge();
    }

}
