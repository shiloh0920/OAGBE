package com.tibame.tga105.rest.restorderstatusmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public class RestOrderStatusService {
	
	private RestOrderStatusDAO_interface dao;

	public RestOrderStatusService() {
		dao = new RestOrderStatusDAO();
	}

	public RestOrderStatusVO addRestorderstatus(String orderstatus) {

		RestOrderStatusVO restOrderStatusVO = new RestOrderStatusVO();
		restOrderStatusVO.setOrderstatus(orderstatus);
		dao.insert(restOrderStatusVO);
		return restOrderStatusVO;
	}

	public void addRestorderstatus(RestOrderStatusVO restOrderStatusVO) {
		dao.insert(restOrderStatusVO);
	}

	public RestOrderStatusVO updateRestorderstatus(Integer orderstatusid, String orderstatus) {

		RestOrderStatusVO restOrderStatusVO = new RestOrderStatusVO();
		restOrderStatusVO.setOrderstatusid(orderstatusid);
		restOrderStatusVO.setOrderstatus(orderstatus);
		dao.update(restOrderStatusVO);
		return dao.findByPrimaryKey(orderstatusid);
	}

	public void deleteRestorderstatus(Integer orderstatusid) {
		dao.delete(orderstatusid);
	}

	public RestOrderStatusVO getOneRestorderstatus(Integer orderstatusid) {
		return dao.findByPrimaryKey(orderstatusid);
	}

	public List<RestOrderStatusVO> getAll() {
		return dao.getAll();
	}
	
	public Set<RestOrderVO> getRestordersByOrderstatusid(Integer orderstatusid) {
		return dao.getRestordersByOrderstatusid(orderstatusid);
	}
}
