package com.github.fshim.testdevjava.service.impl;

import com.github.fshim.testdevjava.domain.TransactionNotification;
import com.github.fshim.testdevjava.event.NotificationEvent;
import com.github.fshim.testdevjava.service.NotificationService;
import com.github.fshim.testdevjava.service.ReportTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaNotificationService implements NotificationService {

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private final ReportTemplate<NotificationEvent> reportTemplate;
    private final String topic;

    public KafkaNotificationService(KafkaTemplate<String, NotificationEvent> kafkaTemplate,
                                    ReportTemplate<NotificationEvent> reportTemplate,
                                    @Value("${kafka.notification-event.name}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.reportTemplate = reportTemplate;
        this.topic = topic;
    }


    @Override
    public void notify(TransactionNotification event) {
        var message = reportTemplate.generateMessage(event);
        try {
            kafkaTemplate.send(topic, message).get();
        } catch (Exception e) {
            log.error("Failed to send notification.", e);
            throw new RuntimeException("Failed to send notification.", e);
        }
        log.info("Send message:{} | topic:{}", message, topic);
    }
}
