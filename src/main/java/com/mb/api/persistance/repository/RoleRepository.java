package com.mb.api.persistance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.api.constants.ERole;
import com.mb.api.persistance.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
	Optional<Role> findByRoleName(ERole roleName);
}
