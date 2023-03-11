package com.tibame.tga105.forum.entity;

import java.util.Date;

import com.tibame.tga105.*;
import com.tibame.tga105.user.entity.UserVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReportEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_id")
	private Integer reportid;
	
	@ManyToOne
	@JoinColumn(name="articleid")
	private ArticleEntity articleEntity;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private UserVO uservo;
	
	@ManyToOne
	@JoinColumn(name=" reporttypeid")
	private ReportTypeEntity reportTypeEntity;
	
	@Column(name="report_detail")
	private String reportdetail;
	
	@Column(name="report_datetime",nullable = false,columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",insertable = false)
	private Date reportdatetime;
	
	
	private Integer status;
	
	private String sign;
	
	private String remark;
	
	@Column(name="submit_datetime",nullable = true ,insertable = true)
	private Date submitdatetime;
	
	

	public Date getSubmitdatetime() {
		return submitdatetime;
	}


	public void setSubmitdatetime(Date submitdatetime) {
		this.submitdatetime = submitdatetime;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getReportid() {
		return reportid;
	}


	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}


	public ArticleEntity getArticleEntity() {
		return articleEntity;
	}


	public void setArticleEntity(ArticleEntity articleEntity) {
		this.articleEntity = articleEntity;
	}


	public UserVO getUservo() {
		return uservo;
	}


	public void setUservo(UserVO uservo) {
		this.uservo = uservo;
	}


	public ReportTypeEntity getReportTypeEntity() {
		return reportTypeEntity;
	}


	public void setReportTypeEntity(ReportTypeEntity reportTypeEntity) {
		this.reportTypeEntity = reportTypeEntity;
	}


	public String getReportdetail() {
		return reportdetail;
	}


	public void setReportdetail(String reportdetail) {
		this.reportdetail = reportdetail;
	}


	public Date getReportdatetime() {
		return reportdatetime;
	}


	public void setReportdatetime(Date reportdatetime) {
		this.reportdatetime = reportdatetime;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
