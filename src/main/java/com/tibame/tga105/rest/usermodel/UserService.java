package com.tibame.tga105.rest.usermodel;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.tibame.tga105.rest.restordermodel.RestOrderVO;



public class UserService {
	private UserDAO_interface dao;

	public UserService() {
		dao = new UserDAO();
	}

	public UserVO addUser(String useremail,String userpwd,String username,String usernickname,String useraddress,
String usertel,Integer userviplevelid,Date userdatetime,boolean userstatus) {

		UserVO userVO = new UserVO();
		
		userVO.setUseremail(useremail);
		userVO.setUserpwd(userpwd);
		userVO.setUsername(username);
		userVO.setUsernickname(usernickname);
		userVO.setUseraddress(useraddress);
		userVO.setUsertel(usertel);
		userVO.setUserviplevelid(userviplevelid);
		userVO.setUserdatetime(userdatetime);
		userVO.setUserstatus(userstatus);
		
		
		
		dao.insert(userVO);
		return userVO;
	}

	public void addUser(UserVO userVO) {
		dao.insert(userVO);
	}

	public UserVO updateUser(Integer userid,String useremail,String userpwd,String username,String usernickname,String useraddress,
			String usertel,Integer userviplevelid,Date userdatetime,boolean userstatus) {

		UserVO userVO = new UserVO();
		userVO.setUserid(userid);
		userVO.setUseremail(useremail);
		userVO.setUserpwd(userpwd);
		userVO.setUsername(username);
		userVO.setUsernickname(usernickname);
		userVO.setUseraddress(useraddress);
		userVO.setUsertel(usertel);
		userVO.setUserviplevelid(userviplevelid);
		userVO.setUserdatetime(userdatetime);
		userVO.setUserstatus(userstatus);
		dao.update(userVO);
		return dao.findByPrimaryKey(userid);
	}

	public void deleteUser(Integer userid) {
		dao.delete(userid);
	}

	public UserVO getOneUser(Integer userid) {
		return dao.findByPrimaryKey(userid);
	}

	public List<UserVO> getAll() {
		return dao.getAll();
	}
	
	public Set<RestOrderVO> getRestordersByUserid(Integer userid) {
		return dao.getRestordersByUserid(userid);
	}
}


