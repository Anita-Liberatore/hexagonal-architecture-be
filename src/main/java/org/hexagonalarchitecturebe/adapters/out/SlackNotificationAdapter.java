package org.hexagonalarchitecturebe.adapters.out;

import org.hexagonalarchitecturebe.domain.ports.out.NotificationDispatcher;
import org.hexagonalarchitecturebe.model.Channel;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SlackNotificationAdapter implements NotificationDispatcher {

    private static final Logger logger = Logger.getLogger(SlackNotificationAdapter.class.getName());

    @Override
    public void send(String recipientDetails, String message) {
        // --- MOCK LOGIC START ---
        logger.info(() -> "\n" +
                "==============================\n" +
                "[MOCK] SlackNotificationAdapter is active\n" +
                "Simulating Slack notification\n" +
                "Recipient (User ID or Channel): " + recipientDetails + "\n" +
                "Message: \"" + message + "\"\n" +
                "==============================\n");
        // --- MOCK LOGIC END ---

        // Real logic (commented out for safety):
        // String payload = "{'text': '" + message + "'}";
        // webClient.post(SLACK_WEBHOOK_URL, payload);
    }

    @Override
    public Channel getSupportedChannel() {
        return Channel.SLACK;
    }
}
