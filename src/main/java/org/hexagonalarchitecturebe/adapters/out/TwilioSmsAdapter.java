package org.hexagonalarchitecturebe.adapters.out;

import org.hexagonalarchitecturebe.domain.ports.out.NotificationDispatcher;
import org.hexagonalarchitecturebe.model.Channel;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class TwilioSmsAdapter implements NotificationDispatcher {

    private static final Logger logger = Logger.getLogger(TwilioSmsAdapter.class.getName());

    @Override
    public void send(String recipientDetails, String message) {
        // --- MOCK LOGIC START ---
        logger.info(() -> "\n" +
                "==============================\n" +
                "[MOCK] TwilioSmsAdapter is active\n" +
                "Simulating SMS send\n" +
                "Recipient: " + recipientDetails + "\n" +
                "Message: \"" + message + "\"\n" +
                "==============================\n");
        // --- MOCK LOGIC END ---

        // Real logic (commented out for safety):
        // Message.creator(
        //     new com.twilio.type.PhoneNumber(recipientDetails), // to
        //     new com.twilio.type.PhoneNumber(twilioFromNumber), // from
        //     message
        // ).create(twilioClient);
    }

    @Override
    public Channel getSupportedChannel() {
        return Channel.SMS;
    }
}