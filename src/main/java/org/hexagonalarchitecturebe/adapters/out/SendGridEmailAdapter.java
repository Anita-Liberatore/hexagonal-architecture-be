package org.hexagonalarchitecturebe.adapters.out;

import org.hexagonalarchitecturebe.domain.ports.out.NotificationDispatcher;
import org.hexagonalarchitecturebe.model.Channel;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SendGridEmailAdapter implements NotificationDispatcher {

    private static final Logger logger = Logger.getLogger(SendGridEmailAdapter.class.getName());

    @Override
    public void send(String recipientDetails, String message) {
        /// --- MOCK LOGIC START ---
        logger.info(() -> "\n" +
                "==============================\n" +
                "[MOCK] SendGridEmailAdapter is active\n" +
                "Simulating email send\n" +
                "Recipient: " + recipientDetails + "\n" +
                "Message: \"" + message + "\"\n" +
                "==============================\n");
        // --- MOCK LOGIC END ---

        // Real logic (commented out for safety):
        // sendGridClient.sendEmail(recipientDetails, "Notification", message);
    }

    @Override
    public Channel getSupportedChannel() {
        return Channel.EMAIL;
    }
}