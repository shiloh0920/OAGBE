package com.tibame.tga105.forum.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tibame.tga105.forum.Repository.LikeStatusRepository;
import com.tibame.tga105.forum.entity.LikeStatusEntity;

@Service
public class LikeStatusService {
	
	@Autowired
	LikeStatusRepository likeStatusRespository;
	
	public LikeStatusEntity  find (Integer articleid,
							Integer userid) {
		return  likeStatusRespository.findOne(articleid, userid);
	}
	
	public LikeStatusEntity add(LikeStatusEntity likeentity) {
		
		return likeStatusRespository.save(likeentity);
	}
}
