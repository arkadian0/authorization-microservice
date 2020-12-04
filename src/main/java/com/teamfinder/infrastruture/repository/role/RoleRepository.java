package com.teamfinder.infrastruture.repository.role;

import com.teamfinder.domain.authorization.Role;
import com.teamfinder.domain.authorization.enums.SystemRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
       Optional<Role> findByName(SystemRole role);
}

