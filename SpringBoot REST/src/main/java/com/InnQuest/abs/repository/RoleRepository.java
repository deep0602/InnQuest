package com.intellect.abs.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intellect.abs.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleNameAndUserId(String roleName, int userId);
}
