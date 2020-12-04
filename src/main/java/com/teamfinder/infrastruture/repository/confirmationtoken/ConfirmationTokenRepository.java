package com.teamfinder.infrastruture.repository.confirmationtoken;

import com.teamfinder.domain.authorization.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String confirmationToken);
}
