package org.hexagonalarchitecturebe.adapters.in.kafka;

import org.hexagonalarchitecturebe.model.Channel;
import org.hexagonalarchitecturebe.model.Notification;

public record KafkaNotificationMessage(
        String customerId,
        String text,
        String channelType
) {
    public Notification toDomainModel() {
        return new Notification(
                this.customerId,
                this.text,
                Channel.valueOf(this.channelType.toUpperCase())
        );
    }
}