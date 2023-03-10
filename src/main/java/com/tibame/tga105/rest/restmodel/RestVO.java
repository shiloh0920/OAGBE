package com.tibame.tga105.rest.restmodel;

import java.util.Arrays;

public class RestVO implements java.io.Serializable{
	
	private Integer restid;
	private String restname;
	private String restaddress;
	private byte[] restimg;
	
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
	}
	public String getRestname() {
		return restname;
	}
	public void setRestname(String restname) {
		this.restname = restname;
	}
	public String getRestaddress() {
		return restaddress;
	}
	public void setRestaddress(String restaddress) {
		this.restaddress = restaddress;
	}
	public byte[] getRestimg() {
		return restimg;
	}
	public void setRestimg(byte[] restimg) {
		this.restimg = restimg;
	}
	@Override
	public String toString() {
		return "RestVO [restid=" + restid + ", restname=" + restname + ", restaddress=" + restaddress + ", restimg="
				+ Arrays.toString(restimg) + "]";
	}
	
	
}
