package com.teamfinder.infrastruture.repository.role;

import com.teamfinder.domain.authorization.Role;
import com.teamfinder.domain.authorization.enums.SystemRole;
import com.teamfinder.domain.authorization.ports.outcoming.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRole(SystemRole role) {
        return roleRepository.findByName(role);
    }
}
