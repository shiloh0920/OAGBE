package com.tibame.tga105.rest.dishmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

public class DishService {

	private DishDAO_interface dao;

	public DishService() {
		dao = new DishDAO();
	}

	public DishVO addDish(Integer restid, String dishname, Integer dishprice,
			Integer dishity, String dishdescription, String dishspec, byte[] dishimg) {

		DishVO dishVO = new DishVO();

		dishVO.setRestid(restid);
		dishVO.setDishname(dishname);
		dishVO.setDishprice(dishprice);
		dishVO.setDishity(dishity);
		dishVO.setDishdescription(dishdescription);
		dishVO.setDishspec(dishspec);
		dishVO.setDishimg(dishimg);
		dao.insert(dishVO);

		return dishVO;
	}
	
	public void addDish(DishVO dishVO) {
		dao.insert(dishVO);
	}

	public DishVO updateDish(Integer dishid, Integer restid, String dishname, Integer dishprice,
			Integer dishity, String dishdescription, String dishspec, byte[] dishimg) {

		DishVO dishVO = new DishVO();

		dishVO.setDishid(dishid);
		dishVO.setRestid(restid);
		dishVO.setDishname(dishname);
		dishVO.setDishprice(dishprice);
		dishVO.setDishity(dishity);
		dishVO.setDishdescription(dishdescription);
		dishVO.setDishspec(dishspec);
		dishVO.setDishimg(dishimg);
		dao.update(dishVO);

//		return dishVO;
		return dao.findByPrimaryKey(dishid);
	}

	public void deleteDish(Integer dishid) {
		dao.delete(dishid);
	}

	public DishVO getOneDish(Integer dishid) {
		return dao.findByPrimaryKey(dishid);
	}

	public List<DishVO> getAll() {
		return dao.getAll();
	}
	
	public Set<RestOrderListVO> getRestorderlistsByDishid(Integer dishid) {
		return dao.getRestorderlistsByDishid(dishid);
	}
}
