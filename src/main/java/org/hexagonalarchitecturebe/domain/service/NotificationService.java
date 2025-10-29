package org.hexagonalarchitecturebe.domain.service;

import org.hexagonalarchitecturebe.domain.ports.in.SendNotificationUseCase;
import org.hexagonalarchitecturebe.domain.ports.out.NotificationDispatcher;
import org.hexagonalarchitecturebe.domain.ports.out.repository.UserPreferenceRepository;
import org.hexagonalarchitecturebe.model.Channel;
import org.hexagonalarchitecturebe.model.Notification;
import org.hexagonalarchitecturebe.model.UserPreferences;
import java.util.logging.Level;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class NotificationService implements SendNotificationUseCase {

    private static final Logger logger = Logger.getLogger(NotificationService.class.getName());

    private final UserPreferenceRepository userRepo;
    private final Map<Channel, NotificationDispatcher> dispatchers;

    public NotificationService(UserPreferenceRepository userRepo, List<NotificationDispatcher> dispatcherList) {
        this.userRepo = userRepo;
        this.dispatchers = dispatcherList.stream()
                .collect(Collectors.toMap(NotificationDispatcher::getSupportedChannel, Function.identity()));
    }

    @Override
    public boolean sendNotification(Notification notification) {
        UserPreferences prefs = userRepo.findByUserId(notification.userId())
                .orElse(UserPreferences.defaultFor(notification.userId()));

        if (!prefs.isSubscribed()) {
            logger.info(() -> String.format("User %s is unsubscribed. Skipping notification.", notification.userId()));
            return false;
        }

        Channel targetChannel = prefs.preferredChannel();
        NotificationDispatcher dispatcher = dispatchers.get(targetChannel);

        if (dispatcher == null) {
            logger.warning(() -> String.format("No dispatcher found for channel %s", targetChannel));
            return false;
        }

        String recipientDetails = getRecipientDetailsForChannel(prefs, targetChannel);
        if (recipientDetails == null) {
            logger.warning(() -> String.format(
                    "No recipient details for user %s and channel %s",
                    prefs.userId(), targetChannel
            ));
            return false;
        }

        try {
            dispatcher.send(recipientDetails, notification.message());
            logger.info(() -> String.format(
                    "Notification sent successfully to user %s via %s",
                    prefs.userId(), targetChannel
            ));
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    String.format("Failed to send notification to user %s via %s: %s",
                            prefs.userId(), targetChannel, e.getMessage()), e);
            return false;
        }
    }

    private String getRecipientDetailsForChannel(UserPreferences prefs, Channel channel) {
        return switch (channel) {
            case EMAIL, SLACK -> prefs.email();
            case SMS -> prefs.phoneNumber();
            case PUSH -> prefs.userId();
        };
    }
}