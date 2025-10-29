package org.hexagonalarchitecturebe.adapters.in.web;

import org.hexagonalarchitecturebe.adapters.in.web.dto.NotificationRequestDTO;
import org.hexagonalarchitecturebe.domain.ports.in.SendNotificationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final SendNotificationUseCase sendNotificationUseCase;

    public NotificationController(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO requestDTO) {
        var notification = requestDTO.toDomainModel();

        boolean success = sendNotificationUseCase.sendNotification(notification);

        if (success) {
            return ResponseEntity.ok("Notification sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send notification.");
        }
    }
}
