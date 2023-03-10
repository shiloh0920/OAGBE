package com.tibame.tga105.rest.restordermodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

public interface RestOrderDAO_interface {
	public int insert(RestOrderVO restOrderVO);

	public void update(RestOrderVO restOrderVO);

	public void delete(Integer orderid);

	public RestOrderVO findByPrimaryKey(Integer orderid);

	public List<RestOrderVO> getAll();

	public Set<RestOrderListVO> getRestorderlistsByOrderid(Integer orderid);

	public void insertWithRestOrderList(RestOrderVO restOrderVO, List<RestOrderListVO> list);
}