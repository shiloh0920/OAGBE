package com.tibame.tga105.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.security.UserPrincipal;

/**
 * Servlet implementation class test1
 */
@WebServlet("/test")
public class test1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
