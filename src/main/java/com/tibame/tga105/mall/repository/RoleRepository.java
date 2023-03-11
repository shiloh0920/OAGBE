package com.tibame.tga105.mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ogabe.mall.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
