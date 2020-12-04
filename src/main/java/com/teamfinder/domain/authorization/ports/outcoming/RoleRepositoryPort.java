package com.teamfinder.domain.authorization.ports.outcoming;

import com.teamfinder.domain.authorization.Role;
import com.teamfinder.domain.authorization.enums.SystemRole;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findByRole(SystemRole role);
}
