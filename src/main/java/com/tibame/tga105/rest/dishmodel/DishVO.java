package com.tibame.tga105.rest.dishmodel;

import java.util.Arrays;
import java.util.Objects;

public class DishVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
private Integer dishid;
	private Integer restid;
	private String dishname;
private Integer dishprice;
	private Integer dishity;
	private String dishdescription;
	private String dishspec;
	private byte[] dishimg;

	public Integer getDishid() {
		return dishid;
	}

	public void setDishid(Integer dishid) {
		this.dishid = dishid;
	}

	public Integer getRestid() {
		return restid;
	}

	public void setRestid(Integer restid) {
		this.restid = restid;
	}

	public String getDishname() {
		return dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}

	public Integer getDishprice() {
		return dishprice;
	}

	public void setDishprice(Integer dishprice) {
		this.dishprice = dishprice;
	}

	public Integer getDishity() {
		return dishity;
	}

	public void setDishity(Integer dishity) {
		this.dishity = dishity;
	}

	public String getDishdescription() {
		return dishdescription;
	}

	public void setDishdescription(String dishdescription) {
		this.dishdescription = dishdescription;
	}

	public String getDishspec() {
		return dishspec;
	}

	public void setDishspec(String dishspec) {
		this.dishspec = dishspec;
	}

	public byte[] getDishimg() {
		return dishimg;
	}

	public void setDishimg(byte[] dishimg) {
		this.dishimg = dishimg;
	}

	@Override
	public String toString() {
		return "DishVO [dishid=" + dishid + ", restid=" + restid + ", dishname=" + dishname + ", dishprice=" + dishprice
				+ ", dishity=" + dishity + ", dishdescription=" + dishdescription + ", dishspec=" + dishspec
				+ ", dishimg=" + Arrays.toString(dishimg) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(dishimg);
		result = prime * result + Objects.hash(dishdescription, dishid, dishname, dishprice, dishity, dishspec, restid);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishVO other = (DishVO) obj;
		return Objects.equals(dishdescription, other.dishdescription) && Objects.equals(dishid, other.dishid)
				&& Arrays.equals(dishimg, other.dishimg) && Objects.equals(dishname, other.dishname)
				&& Objects.equals(dishprice, other.dishprice) && Objects.equals(dishity, other.dishity)
				&& Objects.equals(dishspec, other.dishspec) && Objects.equals(restid, other.restid);
	}

	public com.tibame.tga105.rest.restmodel.RestVO getRestVO() {
		com.tibame.tga105.rest.restmodel.RestService restSvc = new com.tibame.tga105.rest.restmodel.RestService();
		com.tibame.tga105.rest.restmodel.RestVO restVO = restSvc.getOneRest(restid);
		return restVO;
	}

}
