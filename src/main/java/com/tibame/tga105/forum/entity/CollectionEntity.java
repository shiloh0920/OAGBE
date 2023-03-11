package com.tibame.tga105.forum.entity;

import java.util.Date;

import com.tibame.tga105.user.entity.UserVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CollectionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer collectionid;
	
	@Column(name="collection_datetime" ,nullable = false,columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",insertable = false)
	private Date collectiondate;
	
	
	@ManyToOne
	@JoinColumn(name="articleid")
	private ArticleEntity articleEntity;
	
	
	@ManyToOne
	@JoinColumn(name="userid")
	private UserVO uservo;


	public Integer getCollectionid() {
		return collectionid;
	}


	public void setCollectionid(Integer collectionid) {
		this.collectionid = collectionid;
	}


	public Date getCollectiondate() {
		return collectiondate;
	}


	public void setCollectiondate(Date collectiondate) {
		this.collectiondate = collectiondate;
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


	@Override
	public String toString() {
		return "CollectionEntity [collectionid=" + collectionid + ", collectiondate=" + collectiondate
				+ ", articleEntity=" + articleEntity + ", uservo=" + uservo + "]";
	}
	
	
}
