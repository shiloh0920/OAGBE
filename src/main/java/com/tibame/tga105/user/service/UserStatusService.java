package com.tibame.tga105.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tibame.tga105.user.entity.UserStatusVO;
import com.tibame.tga105.user.repository.UserStatusRepository;

@Service
public class UserStatusService {
	
	@Autowired
	UserStatusRepository userUserStatusRepository;

	public UserStatusVO findById(Integer statusid) {
		
		return userUserStatusRepository.findById(statusid).get();
	}


}
