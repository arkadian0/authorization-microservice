package com.teamfinder.infrastruture.repository.confirmationtoken;

import com.teamfinder.domain.authorization.ConfirmationToken;
import com.teamfinder.domain.authorization.ports.outcoming.ConfirmationTokenRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenRepositoryAdapter implements ConfirmationTokenRepositoryPort {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByToken(confirmationToken);
    }
}
