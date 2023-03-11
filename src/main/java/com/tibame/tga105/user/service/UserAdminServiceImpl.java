package com.tibame.tga105.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.repository.AdminUserRepository;
import com.tibame.tga105.user.repository.UserRepository;

@Service
public class UserAdminServiceImpl implements UserAdminService{
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	AdminUserRepository adminUserRepository;

	@Override
	public Page<UserVO> findPaginated(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);

		return userrepo.findAll(pageable);
	}

	@Override
	public Page<UserVO> findPaginatedByEmail(String useremail, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
	
		return adminUserRepository.findByUseremailLike(useremail+"%", pageable);
	}

	@Override
	public Page<UserVO> findPaginatedByUserName(String username, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return adminUserRepository.findByUsernameLike("%"+username+"%", pageable);
	}



}
