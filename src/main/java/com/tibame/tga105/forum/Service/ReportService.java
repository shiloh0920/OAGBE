package com.tibame.tga105.forum.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tibame.tga105.forum.Repository.ReportRepository;
import com.tibame.tga105.forum.entity.ReportEntity;

@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;

	public ReportEntity add(ReportEntity r) {
		return reportRepository.save(r);

	}

	public List<ReportEntity> findall() {
		return reportRepository.findAll();

	}

	public Page<ReportEntity> findAllByPage(Pageable pageable) {

		return reportRepository.findAll(pageable);
	}
	
	public ReportEntity findOne(Integer id) {
		
		return reportRepository.findById(id).get();
	}
	
}
