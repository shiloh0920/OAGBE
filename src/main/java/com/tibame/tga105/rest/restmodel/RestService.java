package com.tibame.tga105.rest.restmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.dishmodel.DishVO;
import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public class RestService {

	private RestDAO_interface dao;

	public RestService() {
		dao = new RestDAO();
	}

	public RestVO addRest(String restname, String restaddress, byte[] restimg) {

		RestVO restVO = new RestVO();

		restVO.setRestname(restname);
		restVO.setRestaddress(restaddress);
		restVO.setRestimg(restimg);
		dao.insert(restVO);

		return restVO;
	}
	
	

	public RestVO updateRest(Integer restid, String restname, String restaddress, byte[] restimg) {

		RestVO restVO = new RestVO();
		
		restVO.setRestid(restid);
		restVO.setRestname(restname);
		restVO.setRestaddress(restaddress);
		restVO.setRestimg(restimg);
		dao.update(restVO);

		return restVO;
	}

	public void deleteRest(Integer restid) {
		dao.delete(restid);
	}

	public RestVO getOneRest(Integer restid) {
		return dao.findByPrimaryKey(restid);
	}

	public List<RestVO> getAll() {
		return dao.getAll();
	}
	
	public Set<DishVO> getDishsByRestid(Integer restid) {
		return dao.getDishsByRestid(restid);
	}
	
	public Set<RestOrderVO> getRestordersByRestid(Integer restid) {
		return dao.getRestordersByRestid(restid);
	}
}
