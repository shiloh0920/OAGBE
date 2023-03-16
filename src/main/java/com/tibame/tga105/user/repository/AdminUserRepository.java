package com.tibame.tga105.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.user.entity.UserStatusVO;
import com.tibame.tga105.user.entity.UserVO;

@Repository
public interface AdminUserRepository extends JpaRepository<UserVO, Integer>{
	List<UserVO> findByUseremailLike(String useremail);
	List<UserVO> findByUsernameLike(String username);
	Page<UserVO> findByUseremailLike(String useremail, Pageable pageable);
	Page<UserVO> findByUsernameLike(String username, Pageable pageable);
	Page<UserVO> findByUserstatusvo(UserStatusVO statusid, Pageable pageable);
	List<UserVO> findByUserstatusvo(UserStatusVO statusid);
}
