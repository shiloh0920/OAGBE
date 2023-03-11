package com.tibame.tga105.rest.usermodel;

import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restordermodel.RestOrderVO;

public interface UserDAO_interface {
	
	public void insert(UserVO userVO);

	public void update(UserVO userVO);

	public void delete(Integer userid);

	public UserVO findByPrimaryKey(Integer userid);

	public List<UserVO> getAll();

	public Set<RestOrderVO> getRestordersByUserid(Integer userid);
}
