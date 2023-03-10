package com.tibame.tga105.user.service;

import org.springframework.data.domain.Page;

import com.tibame.tga105.user.entity.UserVO;

public interface UserAdminService {
	
	Page<UserVO> findPaginated(int pageNo, int pageSize);
	Page<UserVO> findPaginatedByEmail(String useremail,int pageNo, int pageSize);
	Page<UserVO> findPaginatedByUserName(String username,int pageNo, int pageSize);


}
