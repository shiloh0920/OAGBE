package com.tibame.tga105.rest.usermodel;

import java.sql.Date;

public class UserVO implements java.io.Serializable{
	
	private Integer userid;
	private String useremail;
	private String userpwd;
	private String username;
	private String usernickname;
	private String useraddress;
	private String usertel;
	private Integer userviplevelid;
	private Date userdatetime;
	private Boolean userstatus;
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernickname() {
		return usernickname;
	}
	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public Integer getUserviplevelid() {
		return userviplevelid;
	}
	public void setUserviplevelid(Integer userviplevelid) {
		this.userviplevelid = userviplevelid;
	}
	public Date getUserdatetime() {
		return userdatetime;
	}
	public void setUserdatetime(Date userdatetime) {
		this.userdatetime = userdatetime;
	}
	public Boolean getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(Boolean userstatus) {
		this.userstatus = userstatus;
	}
	
	
}
