package com.tibame.tga105.rest.restorderstatusmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public interface RestOrderStatusDAO_interface {
          public void insert(RestOrderStatusVO restOrderStatusVO);
          public void update(RestOrderStatusVO restOrderStatusVO);
          public void delete(Integer orderstatusid);
          public RestOrderStatusVO findByPrimaryKey(Integer orderstatusid);
          public List<RestOrderStatusVO> getAll();
          public Set<RestOrderVO> getRestordersByOrderstatusid(Integer orderstatusid);
}