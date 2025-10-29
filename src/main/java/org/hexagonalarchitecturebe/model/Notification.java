package org.hexagonalarchitecturebe.model;


public record Notification(
        String userId,
        String message,
        Channel type
) {
}