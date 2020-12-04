package com.teamfinder.domain.authorization.ports.outcoming;

import com.teamfinder.domain.authorization.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenRepositoryPort {
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
}
