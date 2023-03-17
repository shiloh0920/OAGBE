package com.tibame.tga105.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.service.UserAdminService;
import com.tibame.tga105.user.service.UserService;
import com.tibame.tga105.user.service.UserStatusService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Autowired
	UserService userService;

	@Autowired
	UserStatusService userStatusService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserAdminService userAdminService;
	
	
	@GetMapping("/login")
	public String login() {

		return "admin_login";
	}
	
	
	@GetMapping("/userlist/{pageNo}")
	public String adminUserList(@PathVariable() int pageNo ,Model m) {
		int pageSize = 3;
		
		Page<UserVO> page = userAdminService.findPaginated(pageNo, pageSize);
		List<UserVO> uservolist = page.getContent();

		m.addAttribute("currentPage", pageNo);
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("totalItems", page.getTotalElements());
		m.addAttribute("uservolist", uservolist);

		return "user_admin_list";

	}
	
	@GetMapping("/edit/{userid}")
	public String goAdminEdit(@PathVariable Integer userid, Model m) {
		UserVO uservo = userService.getUserById(userid);
		m.addAttribute("uservo", uservo);

		return "user_admin_update";
	}
	
	@PostMapping("/edituser")
	public String adminedituser(@RequestParam("userid") Integer userid, @RequestParam("username") String username,
			@RequestParam("usernickname") String usernickname, @RequestParam("useraddress") String useraddress,
			@RequestParam("usertel") String usertel, @RequestParam("userstatus") Integer userstatus, @RequestParam("userpic") MultipartFile file)
			throws IOException {

		UserVO tempUservo = userService.getUserById(userid);
		
		tempUservo.setUsername(username);
		tempUservo.setUsernickname(usernickname);
		tempUservo.setUseraddress(useraddress);
		tempUservo.setUsertel(usertel);
		Integer userStatusID = userstatus;
		byte[] userpic = file.getBytes();
		if (file.getOriginalFilename() != "") {
			tempUservo.setUserpic(userpic);
		}

		userService.adminUserEdit(userStatusID, tempUservo);

		return "redirect:/admin/user/userlist/1";
	}
	
	@GetMapping("/searchuser/{pageNo}")
	public String searchUser(@RequestParam(name="searchID") String searchID,
			@RequestParam(name="searchValue") String searchValue,
			Model m, @PathVariable() int pageNo) {
		int pageSize = 3;
		
		List<UserVO> uservolist = new ArrayList<UserVO>();
		if(searchID.equals("useremail")) {
//			uservolist = userService.getUserListByEmail(searchValue+"%");	
			Page<UserVO> page = userAdminService.findPaginatedByEmail(searchValue, pageNo, pageSize);
			uservolist = page.getContent();
			m.addAttribute("uservolist", uservolist);	
			m.addAttribute("totalPages", page.getTotalPages());
			m.addAttribute("totalItems", page.getTotalElements());
		}else if(searchID.equals("username")){
			Page<UserVO> page = userAdminService.findPaginatedByUserName(searchValue, pageNo, pageSize);
			uservolist = page.getContent();			
			m.addAttribute("uservolist", uservolist);
			m.addAttribute("totalPages", page.getTotalPages());
			m.addAttribute("totalItems", page.getTotalElements());
		}
		if (uservolist.size()==0) {
			m.addAttribute("errormsg", "notfind");
			return "user_admin_searchlist";
			
		}
		
		m.addAttribute("currentPage", pageNo);
		m.addAttribute("searchID", searchID);
		m.addAttribute("searchValue", searchValue);

		return "user_admin_searchlist";
	}
	
	@GetMapping("/test/{pageNo}")
	public String findPaginated(@PathVariable() int pageNo, Model m) {
		int pageSize = 3;
		
		Page<UserVO> page = userAdminService.findPaginated(pageNo, pageSize);
//		List<UserVO> uservolist = page.getContent();
		List<UserVO> uservolist = userService.getAllUser();
		m.addAttribute("currentPage", pageNo);
		m.addAttribute("totalPages", page.getTotalPages());
		m.addAttribute("totalItems", page.getTotalElements());
		m.addAttribute("uservolist", uservolist);
		
		return "user_admin_list";
		
	}
	
//	@GetMapping("/finduncertifyuser/{pageNo}")
//	public String findUncertifyUsers(Model m, @PathVariable() int pageNo) {
//		
//		int pageSize = 3;
//		Page<UserVO> page = userAdminService.findUncertifyUsers(pageNo, pageSize);
//		List<UserVO> uservolist = page.getContent();
//
//		m.addAttribute("currentPage", pageNo);
//		m.addAttribute("totalPages", page.getTotalPages());
//		m.addAttribute("totalItems", page.getTotalElements());
//		m.addAttribute("uservolist", uservolist);
//
//		return "user_admin_list";
//
//	}
	
	@GetMapping("/finduncertifyuser")
	public String findUncertifyUsers(Model m) {
		
		List<UserVO> uservolist = userAdminService.findUncertifyUsers();

		m.addAttribute("uservolist", uservolist);

		return "user_admin_list";

	}

}
