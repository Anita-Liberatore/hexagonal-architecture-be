package org.hexagonalarchitecturebe.adapters.in.web.dto;

import org.hexagonalarchitecturebe.model.Channel;
import org.hexagonalarchitecturebe.model.Notification;

public record NotificationRequestDTO(
        String userId,
        String message,
        String type
) {
    public Notification toDomainModel() {
        return new Notification(
                this.userId,
                this.message,
                Channel.valueOf(this.type.toUpperCase())
        );
    }
}