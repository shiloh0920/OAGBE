package com.tibame.tga105.rest.restordermodel;

import java.sql.Timestamp;

public class RestOrderVO implements java.io.Serializable{
	
	
	private Integer orderid;
	private Integer userid;
	private Integer restid;
	private Integer orderstatusid;
	private Timestamp ordertime;
	private String ordermemo;

	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
	}
	public Integer getOrderstatusid() {
		return orderstatusid;
	}
	public void setOrderstatusid(Integer orderstatusid) {
		this.orderstatusid = orderstatusid;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public String getOrdermemo() {
		return ordermemo;
	}
	public void setOrdermemo(String ordermemo) {
		this.ordermemo = ordermemo;
	}
	
	
	@Override
	public String toString() {
		return "RestOrderVO [orderid=" + orderid + ", userid=" + userid + ", restid=" + restid + ", orderstatusid="
				+ orderstatusid + ", ordertime=" + ordertime + ", ordermemo=" + ordermemo + "]";
	}
	
	public com.tibame.tga105.rest.restmodel.RestVO getRestVO() {
		com.tibame.tga105.rest.restmodel.RestService restSvc = new com.tibame.tga105.rest.restmodel.RestService();
		com.tibame.tga105.rest.restmodel.RestVO restVO = restSvc.getOneRest(restid);
		return restVO;
	}
	
	public com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusVO getRestOrderStatusVO() {
		com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusService restOrderStatusSvc = new com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusService();
		com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusVO restOrderStatusVO = restOrderStatusSvc.getOneRestorderstatus(orderstatusid);
	    return restOrderStatusVO;
    }
	
	public com.tibame.tga105.rest.usermodel.UserVO getUserVO() {
		com.tibame.tga105.rest.usermodel.UserService userSvc = new com.tibame.tga105.rest.usermodel.UserService();
		com.tibame.tga105.rest.usermodel.UserVO userVO = userSvc.getOneUser(userid);
	    return userVO;
    }
}
