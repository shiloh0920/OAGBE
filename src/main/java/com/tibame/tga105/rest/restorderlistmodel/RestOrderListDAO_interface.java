package com.tibame.tga105.rest.restorderlistmodel;

import java.util.*;

public interface RestOrderListDAO_interface {
          public void insert(RestOrderListVO restOrderListVO);
          public void update(RestOrderListVO restOrderListVO);
          public void delete(Integer orderlistid);
          public RestOrderListVO findByPrimaryKey(Integer orderlistid);
          public List<RestOrderListVO> getAll();
          
          public void insert2(RestOrderListVO restOrderListVO, java.sql.Connection con);
        
}