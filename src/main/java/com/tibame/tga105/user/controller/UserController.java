package com.tibame.tga105.user.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import com.tibame.tga105.user.security.UserPrincipal;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.entity.VerificationToken;
import com.tibame.tga105.user.model.UserModel;
import com.tibame.tga105.user.service.EmailService;
import com.tibame.tga105.user.service.UserService;
import com.tibame.tga105.user.service.UserStatusService;
import com.tibame.tga105.user.service.VerificationService;
import com.tibame.tga105.user.ultility.SiteUrl;
import com.tibame.tga105.user.ultility.UserVONotFindException;

import net.bytebuddy.utility.RandomString;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@Autowired
	VerificationService verificationService;

	@Autowired
	UserStatusService userStatusService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@GetMapping("/")
	public String home() {
		
		

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserPrincipal) {
			username = ((UserPrincipal) principal).getUsername();
			UserVO uservo = ((UserPrincipal) principal).getUservo();
//			System.err.println(uservo.getUserpwd());
		} else {
			username = principal.toString();
//			System.err.println(username);
		}
		// 若沒登入，security回傳的username會是anonymousUser
		if (username.equals("anonymousUser")) {
			System.out.println("沒登入狀態");
		} else {
			System.out.println("有登入狀態");
		}

		return "index";
	}

	@GetMapping("/login")
	public String login() {

		return "user_login";
	}

	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "accessDenied";
	}

	@GetMapping("/register")
	public String register(Model m) {
		return "user_register";
	}
	
	@PostMapping("/register")
	public String regiTest(@ModelAttribute UserModel usermodel, Model m, HttpServletRequest req) throws UnknownHostException {

				
		String useremail = usermodel.getUseremail();

		if(userService.getUserByEmail(useremail)!=null) {
			m.addAttribute("usermodel", usermodel);
			return "user_register";
		}

		else {
			UserVO uservo = userService.register(usermodel);

			VerificationToken verificationToken = new VerificationToken(uservo);

			verificationService.saveToken(verificationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(uservo.getUseremail());
			mailMessage.setSubject("Ogabe會員註冊驗證信!");
			mailMessage.setFrom("TGA105-Ogabe");
			mailMessage.setText("請點選以下連結成為Ogabe正式會員，並享有更多會員服務 : " + SiteUrl.getSiteURL(req)
					+ "/confirm-account?token=" + verificationToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);
			
			return "redirect:/login?register=success";
		}
	}

	@GetMapping("/forgetpassword")
	public String showForgetPasswordForm() {
		return "user_forgetpassword";
	}

	@PostMapping("/forgetpassword")
	public String processForgetPassword(@RequestParam(name = "useremail") String useremail, Model m,
			HttpServletRequest req) {
		String email = useremail;
		String token = RandomString.make(50);
		try {
			userService.updateResetPassword(token, email);
			String resetPasswordLink = SiteUrl.getSiteURL(req) + "/resetPassword?token=" + token;
			String sender = "tibame105ogabe@gmail.com";
			String mailSubject = "OGABE 會員密碼重新設定";
			String mailContent = "<p>親愛的會員您好,</p>" + "本站已收到需要重新設定密碼的請求," + "請點擊下方連結設定新密碼: " + "<br>" + "<a href=\""
					+ resetPasswordLink + "\" > 重設密碼 </a>" + "<br>" + "若不需重新設定請忽略此信件~";

			//new Thread(() -> {
				try {
					emailService.sendEmail(email, resetPasswordLink, sender, mailSubject, mailContent);
					m.addAttribute("message", "已寄送重設密碼的連結到您的信箱");
				} catch (MessagingException e) {
					m.addAttribute("message", "寄送失敗，請稍後在試");
				}

			//}).start();

		} catch (UserVONotFindException e) {
			m.addAttribute("error", e.getMessage());
			
		}
		return "user_forgetpassword";
		
	}

	@GetMapping("/resetPassword")
	public String resetPasswordform(@RequestParam(value = "token") String token, Model m) {

		UserVO uservo = userService.getUserByResetPasswordToken(token);

		if (uservo != null) {
			m.addAttribute("token", token);
			return "user_resetpassword";
		} else {
			return "redirect:/login?resetpassword=fail";
		}
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam(name = "token") String token,
			@RequestParam(name = "userpwd") String userpwd, Model m) {

		UserVO uservo = userService.getUserByResetPasswordToken(token);
		
		if (uservo != null) {
			
			userService.resetPassword(uservo, userpwd);
//			m.addAttribute("msg", "密碼重設成功!!");
			
		}

		return "redirect:/login?resetpassword=success";
	}

	@GetMapping("/userpage")
	public String userPage(Model m) {

		UserVO uservo = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserPrincipal) {
			uservo = userService.getUserByEmail(((UserPrincipal) principal).getUsername());
		} 
		

		m.addAttribute("uservo", uservo);

		return "user_data";

	}

	@PostMapping("/useredit")
	public String userEdit(@RequestParam("username") String username, @RequestParam("usernickname") String usernickname,
			@RequestParam("useraddress") String useraddress, @RequestParam("usertel") String usertel,
			@RequestParam("userpic") MultipartFile file, Model m) throws IOException {

		String useremail = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			useremail = ((UserDetails) principal).getUsername();
		} else {
			useremail = principal.toString();
		}
	
		UserVO temp = userService.getUserByEmail(useremail);
		

		temp.setUsername(username);
		temp.setUsernickname(usernickname);
		temp.setUseraddress(useraddress);
		temp.setUsertel(usertel);
		if (file.getOriginalFilename() != "") {
			temp.setUserpic(file.getBytes());
		}

		userService.saveUser(temp);
		m.addAttribute("uservo", temp);
		return "user_data";
	}

	@GetMapping("/userpage/userchangepassword")
	public String userChangePassword(Model m) {
		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO uservo = userService.getUserById(principal.getUservo().getUserid());
		m.addAttribute("uservo", uservo);

		return "user_data_changepassword";
	}
	
	@PostMapping("/userpage/userchangepassword")
	public String ChangePassword(Model m, @RequestParam(name="oldpassword") String oldpassword, @RequestParam(name="newpassword") String newpassword) {
		
		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO uservo = principal.getUservo();
		String oldpwd = oldpassword;
		String newpwd = newpassword;
		
		if(!passwordEncoder.matches(oldpwd, uservo.getUserpwd())) {
			System.out.println("密碼有誤!!");
			return "redirect:/userpage/userchangepassword?changepassword=error";
		}else if(passwordEncoder.matches(newpwd, uservo.getUserpwd())){
			System.out.println("新舊密碼不可相同!!");
			return "redirect:/userpage/userchangepassword?changepassword=notchange";
		}
		UserVO temp = userService.getUserById(uservo.getUserid());
		temp.setUserpwd(passwordEncoder.encode(newpwd));
		uservo = userService.saveUser(temp);
	
		m.addAttribute("uservo", uservo);
		m.addAttribute("msg", "success");
		return "user_data_changepassword";
	}


	@GetMapping("/image/display/{userid}")
	@ResponseBody
	public void showUserImg(@PathVariable Integer userid, HttpServletResponse res, UserVO uservo)
			throws ServletException, IOException {

		uservo = userService.getUserById(userid);
		res.setContentType("image/jped, image/jpg, image/png, image/gif");
		
		if(uservo.getUserpic()==null) {
			Resource resource = new ClassPathResource("\\static\\images\\user_default_pic.png");
			String defaultPicPath = resource.getFile().getPath();
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(defaultPicPath)));
			byte[] defaultPic = bis.readAllBytes();
			res.getOutputStream().write(defaultPic);
			bis.close();
		}else {
			res.getOutputStream().write(uservo.getUserpic());
		}
		
		res.getOutputStream().close();

	}

	@GetMapping("/confirm-account")
	public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
		VerificationToken token = verificationService.findByConfirmationToken(confirmationToken);

		if (token != null) {
			UserVO user = userService.getUserByEmail(token.getUservo().getUseremail());
			user.setUserstatusvo(userStatusService.findById(3));
			userService.saveUser(user);
			
			return "redirect:/login?verify=success";
			
		} else {
	
			return "redirect:/login?verify=fail";
		}
	}
	
	@GetMapping("userpage/resendUserVerficationEmail")
	public String resendUserVerficationEmail(HttpServletRequest req, Model m) {

		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserVO uservo = userService.getUserById(principal.getUservo().getUserid());
		if (uservo.getUserstatusvo().getStatusid()!=2) {
			m.addAttribute("uservo", uservo);
			return "redirect:/userpage?verification=already";
		}else {
			VerificationToken verificationToken = new VerificationToken(uservo);
			verificationService.saveToken(verificationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(uservo.getUseremail());
			mailMessage.setSubject("Ogabe會員註冊驗證信!");
			mailMessage.setFrom("TGA105-Ogabe");
			mailMessage.setText("請點選以下連結成為Ogabe正式會員，並享有更多會員服務 : " + SiteUrl.getSiteURL(req)
					+ "/confirm-account?token=" + verificationToken.getConfirmationToken());

			emailService.sendEmail(mailMessage);
			return "redirect:/userpage?resend=success";
		}

	}

}
