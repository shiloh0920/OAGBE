package com.tibame.tga105.rest.dishmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

public interface DishDAO_interface {
          public void insert(DishVO dishVO);
          public void update(DishVO dishVO);
          public void delete(Integer dishid);
          public DishVO findByPrimaryKey(Integer dishid);
          public List<DishVO> getAll();
          public Set<RestOrderListVO> getRestorderlistsByDishid(Integer dishid);
        
}
