package com.tibame.tga105.forum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Popularity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer popularid;
	
	
	@JoinColumn(name="articleid")
	@ManyToOne
	private ArticleEntity articleEntity;
}
