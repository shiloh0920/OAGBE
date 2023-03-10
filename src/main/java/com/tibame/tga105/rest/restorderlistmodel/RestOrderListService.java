package com.tibame.tga105.rest.restorderlistmodel;

import java.util.List;

public class RestOrderListService {

	private RestOrderListDAO_interface dao;

	public RestOrderListService() {
		dao = new RestOrderListDAO();
	}

	public RestOrderListVO addRestorderlist(Integer orderid, Integer dishid, Integer dishprice, Integer dishqty) {

		RestOrderListVO restOrderListVO = new RestOrderListVO();

		restOrderListVO.setOrderid(orderid);
		restOrderListVO.setDishid(dishid);
		restOrderListVO.setDishprice(dishprice);
		restOrderListVO.setDishqty(dishqty);

		dao.insert(restOrderListVO);

		return restOrderListVO;
	}

	public void addRestorderlist(RestOrderListVO restOrderListVO) {
		dao.insert(restOrderListVO);
	}

	public RestOrderListVO updateRestorderlist(Integer orderlistid, Integer orderid, Integer dishid, Integer dishprice,
			Integer dishqty) {

		RestOrderListVO restOrderListVO = new RestOrderListVO();

		restOrderListVO.setOrderlistid(orderlistid);
		restOrderListVO.setOrderid(orderid);
		restOrderListVO.setDishid(dishid);
		restOrderListVO.setDishprice(dishprice);
		restOrderListVO.setDishqty(dishqty);
		dao.update(restOrderListVO);

		return dao.findByPrimaryKey(orderlistid);
	}

	public void deleteRestorderlist(Integer orderlistid) {
		dao.delete(orderlistid);
	}

	public RestOrderListVO getOneRestorderlist(Integer orderlistid) {
		return dao.findByPrimaryKey(orderlistid);
	}

	public List<RestOrderListVO> getAll() {
		return dao.getAll();
	}

	public void insert2(RestOrderListVO restOrderListVO, java.sql.Connection con) {
		dao.insert2(restOrderListVO, con);
	};

}
