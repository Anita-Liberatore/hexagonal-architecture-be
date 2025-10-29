package org.hexagonalarchitecturebe.adapters.out.persistence;

import org.hexagonalarchitecturebe.domain.ports.out.repository.UserPreferenceRepository;
import org.hexagonalarchitecturebe.model.Channel;
import org.hexagonalarchitecturebe.model.UserPreferences;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserPreferenceAdapter implements UserPreferenceRepository {

    private final Map<String, UserPreferences> preferencesDb = new ConcurrentHashMap<>();

    public InMemoryUserPreferenceAdapter() {
        preferencesDb.put("user-123", new UserPreferences(
                "user-123", Channel.EMAIL, true, "user123@gmail.com", "+111111"
        ));
        preferencesDb.put("user-456", new UserPreferences(
                "user-456", Channel.SMS, true, "user456@hotmail.com", "+222222"
        ));
        preferencesDb.put("user-789", new UserPreferences(
                "user-789", Channel.EMAIL, false, "unsubscribed@aol.com", "+333333" // Utente non iscritto
        ));
    }

    @Override
    public Optional<UserPreferences> findByUserId(String userId) {
        return Optional.ofNullable(preferencesDb.get(userId));
    }
}