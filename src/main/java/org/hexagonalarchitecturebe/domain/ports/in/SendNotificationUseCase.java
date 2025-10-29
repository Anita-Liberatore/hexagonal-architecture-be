package org.hexagonalarchitecturebe.domain.ports.in;

import org.hexagonalarchitecturebe.model.Notification;

public interface SendNotificationUseCase {
    boolean sendNotification(Notification notification);
}
