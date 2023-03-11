package com.tibame.tga105.forum.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.forum.entity.ArticleEntity;
import com.tibame.tga105.forum.entity.ReportEntity;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity,Integer> {
	
	
	Page<ReportEntity> findAll (Pageable pageable);
}
