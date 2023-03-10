package com.tibame.tga105.rest.restmodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.dishmodel.DishVO;
import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public interface RestDAO_interface {
          public void insert(RestVO restVO);
          public void update(RestVO restVO);
          public void delete(Integer restid);
          public RestVO findByPrimaryKey(Integer restid);
          public List<RestVO> getAll();
          public Set<DishVO> getDishsByRestid(Integer restid);
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
          public Set<RestOrderVO> getRestordersByRestid(Integer restid);
 
}
