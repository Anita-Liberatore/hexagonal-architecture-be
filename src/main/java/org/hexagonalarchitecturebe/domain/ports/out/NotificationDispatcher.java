package org.hexagonalarchitecturebe.domain.ports.out;

import org.hexagonalarchitecturebe.model.Channel;

public interface NotificationDispatcher {
    void send(String recipientDetails, String message);
    Channel getSupportedChannel();
}
