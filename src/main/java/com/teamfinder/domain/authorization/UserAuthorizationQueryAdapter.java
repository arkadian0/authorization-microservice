package com.teamfinder.domain.authorization;


import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class UserAuthorizationQueryAdapter implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> user = userRepositoryPort.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User not found by name: %s", email));
        }
        return Account.of(user.get());
    }
}
