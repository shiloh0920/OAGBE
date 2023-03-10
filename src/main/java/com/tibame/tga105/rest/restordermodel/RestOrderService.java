package com.tibame.tga105.rest.restordermodel;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

public class RestOrderService {

	private RestOrderDAO_interface dao;

	public RestOrderService() {
		dao = new RestOrderDAO();
	}

	public RestOrderVO addRestorder(Integer userid, Integer restid, Integer orderstatusid, Timestamp ordertime,
			String ordermemo) {

		RestOrderVO restOrderVO = new RestOrderVO();
		
		restOrderVO.setUserid(userid);
		restOrderVO.setRestid(restid);
		restOrderVO.setOrderstatusid(orderstatusid);
		restOrderVO.setOrdertime(ordertime);
		restOrderVO.setOrdermemo(ordermemo);
		int order_id = dao.insert(restOrderVO);
		if (order_id != -1) {
			restOrderVO.setOrderid(order_id);
		}
		return restOrderVO;
	}

	public void addRestorder(RestOrderVO restOrderVO) {
		dao.insert(restOrderVO);
	}

	public RestOrderVO updateRestorder(Integer orderid, Integer userid, Integer restid, Integer orderstatusid, Timestamp ordertime,
			String ordermemo) {

		RestOrderVO restOrderVO = new RestOrderVO();

		restOrderVO.setOrderid(orderid);
		restOrderVO.setUserid(userid);
		restOrderVO.setRestid(restid);
		restOrderVO.setOrderstatusid(orderstatusid);
		restOrderVO.setOrdertime(ordertime);
		restOrderVO.setOrdermemo(ordermemo);
		dao.update(restOrderVO);

//		return dishVO;
		return dao.findByPrimaryKey(orderid);
	}

	public void deleteRestorder(Integer orderid) {
		dao.delete(orderid);
	}

	public RestOrderVO getOneRestorder(Integer orderid) {
		return dao.findByPrimaryKey(orderid);
	}

	public List<RestOrderVO> getAll() {
		return dao.getAll();
	}

	public Set<RestOrderListVO> getRestorderlistsByOrderid(Integer orderid) {
		return dao.getRestorderlistsByOrderid(orderid);
   	}
	 
	public void insertWithRestOrderList(RestOrderVO restOrderVO,  List<RestOrderListVO> list) {
		dao.insertWithRestOrderList(restOrderVO,list);
	 }; 
}
