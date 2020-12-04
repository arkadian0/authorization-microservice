package com.teamfinder.infrastruture.repository.user;

import com.teamfinder.domain.authorization.Account;
import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<Account> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Account save(Account preparedUser) {
        return userRepository.save(preparedUser);
    }
}
