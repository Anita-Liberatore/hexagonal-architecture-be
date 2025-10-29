package org.hexagonalarchitecturebe.domain.ports.out.repository;

import org.hexagonalarchitecturebe.model.UserPreferences;

import java.util.Optional;

public interface UserPreferenceRepository {
    Optional<UserPreferences> findByUserId(String userId);
}
