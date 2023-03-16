package com.tibame.tga105.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tibame.tga105.user.entity.UserVO;

public interface UserAdminService {
	
	Page<UserVO> findPaginated(int pageNo, int pageSize);
	Page<UserVO> findPaginatedByEmail(String useremail,int pageNo, int pageSize);
	Page<UserVO> findPaginatedByUserName(String username,int pageNo, int pageSize);
	Page<UserVO> findUncertifyUsers(int pageNo, int pageSize);
	List<UserVO> findUncertifyUsers();


}
