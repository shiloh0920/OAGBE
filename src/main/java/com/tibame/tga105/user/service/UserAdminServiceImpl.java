package com.tibame.tga105.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tibame.tga105.user.entity.UserStatusVO;
import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.repository.AdminUserRepository;
import com.tibame.tga105.user.repository.UserRepository;
import com.tibame.tga105.user.repository.UserStatusRepository;

@Service
public class UserAdminServiceImpl implements UserAdminService{
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	AdminUserRepository adminUserRepository;
	
	@Autowired
	UserStatusRepository statusRepo;

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

	@Override
	public Page<UserVO> findUncertifyUsers(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		UserStatusVO userstatusVO = statusRepo.findById(2).get();;
		
		return adminUserRepository.findByUserstatusvo(userstatusVO, pageable);
	}

	@Override
	public List<UserVO> findUncertifyUsers() {
		UserStatusVO userstatusVO = statusRepo.findById(2).get();
		return adminUserRepository.findByUserstatusvo(userstatusVO);
	}



}
