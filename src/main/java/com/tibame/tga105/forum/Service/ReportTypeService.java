package com.tibame.tga105.forum.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tibame.tga105.forum.Repository.ReportTypeRepository;
import com.tibame.tga105.forum.entity.ReportTypeEntity;

@Service
public class ReportTypeService {
	
	@Autowired
	ReportTypeRepository rtr;
	
	public List<ReportTypeEntity> findall(){
		
		return rtr.findAll();
		
	}
	
	public ReportTypeEntity findById(Integer id) {
		
		return rtr.findById(id).get();
	}
}
