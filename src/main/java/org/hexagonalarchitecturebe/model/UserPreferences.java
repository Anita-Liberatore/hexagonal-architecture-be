package org.hexagonalarchitecturebe.model;

public record UserPreferences(
        String userId,
        Channel preferredChannel,
        boolean isSubscribed,
        String email,
        String phoneNumber
) {

    public static UserPreferences defaultFor(String userId) {
        return new UserPreferences(userId, Channel.EMAIL, true, "default.user@example.com", "+1234567890");
    }
}
