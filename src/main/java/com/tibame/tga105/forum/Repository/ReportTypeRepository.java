package com.tibame.tga105.forum.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tibame.tga105.forum.entity.ReportTypeEntity;

@Repository
public interface ReportTypeRepository extends JpaRepository<ReportTypeEntity, Integer> {

}
