package com.tibame.tga105.rest.restorderlistmodel;

public class RestOrderListVO implements java.io.Serializable{
	
	private Integer orderlistid;
	private Integer orderid;
private Integer dishid;
private Integer dishprice;
private Integer dishqty;
	
	public Integer getOrderlistid() {
		return orderlistid;
	}
	public void setOrderlistid(Integer orderlistid) {
		this.orderlistid = orderlistid;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getDishid() {
		return dishid;
	}
	public void setDishid(Integer dishid) {
		this.dishid = dishid;
	}
	public Integer getDishprice() {
		return dishprice;
	}
	public void setDishprice(Integer dishprice) {
		this.dishprice = dishprice;
	}
	public Integer getDishqty() {
		return dishqty;
	}
	public void setDishqty(Integer dishqty) {
		this.dishqty = dishqty;
	}
	
	public com.tibame.tga105.rest.dishmodel.DishVO getDishVO() {
		com.tibame.tga105.rest.dishmodel.DishService dishSvc = new com.tibame.tga105.rest.dishmodel.DishService();
		com.tibame.tga105.rest.dishmodel.DishVO dishVO = dishSvc.getOneDish(dishid);
	    return dishVO;
    }
	
	public com.tibame.tga105.rest.restordermodel.RestOrderVO getRestOrderVO() {
		com.tibame.tga105.rest.restordermodel.RestOrderService restOrderSvc = new com.tibame.tga105.rest.restordermodel.RestOrderService();
		com.tibame.tga105.rest.restordermodel.RestOrderVO restOrderVO = restOrderSvc.getOneRestorder(orderid);
	    return restOrderVO;
    }
	
}
