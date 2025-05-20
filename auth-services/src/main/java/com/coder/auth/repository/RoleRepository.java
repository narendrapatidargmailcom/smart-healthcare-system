package com.coder.auth.repository;

import com.coder.auth.model.Role;
import com.coder.auth.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(UserRole name);
}
