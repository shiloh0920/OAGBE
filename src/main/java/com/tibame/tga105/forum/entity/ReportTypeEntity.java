package com.tibame.tga105.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportTypeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_type_id")
	private Integer reporttypeid;
	
	@Column(name="report_type_detail")
	private String reporttypedetail;

	public Integer getReporttypeid() {
		return reporttypeid;
	}

	public void setReporttypeid(Integer reporttypeid) {
		this.reporttypeid = reporttypeid;
	}

	public String getReporttypedetail() {
		return reporttypedetail;
	}

	public void setReporttypedetail(String reporttypedetail) {
		this.reporttypedetail = reporttypedetail;
	}
	
	
}
