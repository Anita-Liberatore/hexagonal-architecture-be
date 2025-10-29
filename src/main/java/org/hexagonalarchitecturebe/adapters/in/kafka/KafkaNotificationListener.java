package org.hexagonalarchitecturebe.adapters.in.kafka;

import org.hexagonalarchitecturebe.domain.ports.in.SendNotificationUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationListener {

    private final SendNotificationUseCase sendNotificationUseCase;

    public KafkaNotificationListener(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @KafkaListener(topics = "notification-requests", groupId = "notification-consumers")
    public void handleKafkaMessage(KafkaNotificationMessage message) {
        System.out.println("Received Kafka message: " + message);

        var notification = message.toDomainModel();

        sendNotificationUseCase.sendNotification(notification);
    }
}
