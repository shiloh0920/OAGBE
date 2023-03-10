package com.tibame.tga105.rest.restorderstatusmodel;


public class RestOrderStatusVO implements java.io.Serializable{
	
	
	private Integer orderstatusid;
	private String orderstatus;
	
	public Integer getOrderstatusid() {
		return orderstatusid;
	}
	public void setOrderstatusid(Integer orderstatusid) {
		this.orderstatusid = orderstatusid;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
	@Override
	public String toString() {
		return "RestOrderStatusVO [orderstatusid=" + orderstatusid + ", orderstatus=" + orderstatus + "]";
	}
	
	
}
