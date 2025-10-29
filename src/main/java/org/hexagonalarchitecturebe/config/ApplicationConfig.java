package org.hexagonalarchitecturebe.config;

import org.hexagonalarchitecturebe.domain.ports.in.SendNotificationUseCase;
import org.hexagonalarchitecturebe.domain.ports.out.NotificationDispatcher;
import org.hexagonalarchitecturebe.domain.ports.out.repository.UserPreferenceRepository;
import org.hexagonalarchitecturebe.domain.service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public SendNotificationUseCase notificationService(
            UserPreferenceRepository userPreferenceRepository,
            List<NotificationDispatcher> dispatchers) {


        return new NotificationService(userPreferenceRepository, dispatchers);
    }
}