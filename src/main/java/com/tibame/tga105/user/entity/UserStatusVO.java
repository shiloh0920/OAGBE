package com.tibame.tga105.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_status")
public class UserStatusVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer statusid;
	@Column
	private String rolename;

	@OneToMany(mappedBy="userstatusvo", cascade=CascadeType.ALL)
	private Set<UserVO> uservo;

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<UserVO> getUservo() {
		return uservo;
	}

	public void setUservo(Set<UserVO> uservo) {
		this.uservo = uservo;
	}

	public UserStatusVO(Integer statusid, String rolename, Set<UserVO> uservo) {
		super();
		this.statusid = statusid;
		this.rolename = rolename;
		this.uservo = uservo;
	}

	public UserStatusVO() {
		super();
	}
	
	
	
}
