package com.tibame.tga105.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.user.entity.UserStatusVO;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusVO, Integer> {

}
