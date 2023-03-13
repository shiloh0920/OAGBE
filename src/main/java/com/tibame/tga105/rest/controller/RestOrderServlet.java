package com.tibame.tga105.rest.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tibame.tga105.rest.dishmodel.DishVO;
import com.tibame.tga105.rest.restmodel.RestService;
import com.tibame.tga105.rest.restmodel.RestVO;
import com.tibame.tga105.rest.restorderlistmodel.RestOrderListService;
import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;
import com.tibame.tga105.rest.restordermodel.RestOrderService;
import com.tibame.tga105.rest.restordermodel.RestOrderVO;
import com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusService;
import com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusVO;
import com.tibame.tga105.user.entity.UserVO;
import com.tibame.tga105.user.security.UserPrincipal;
import com.tibame.tga105.user.service.UserService;


@WebServlet("/restOrder.do")
public class RestOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("orderid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("orderid","請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restordercmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer orderid = null;
				try {
					orderid = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("orderid","訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restordercmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RestOrderService restOrderSvc = new RestOrderService();
				RestOrderVO restOrderVO = restOrderSvc.getOneRestorder(orderid);
				if (restOrderVO == null) {
					errorMsgs.put("orderid","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restordercmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderVO", restOrderVO); // 資料庫取出的restOrderVO物件,存入req
				String url = "restordercmslistone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer orderid = Integer.valueOf(req.getParameter("orderid"));
				
				/***************************2.開始查詢資料****************************************/
				RestOrderService restOrderSvc = new RestOrderService();
				RestOrderVO restOrderVO = restOrderSvc.getOneRestorder(orderid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?orderid="  		+restOrderVO.getOrderid()+
						       "&userid="  			+restOrderVO.getUserid()+
						       "&restid="    		+restOrderVO.getRestid()+
						       "&orderstatusid="	+restOrderVO.getOrderstatusid()+
						       "&ordertime="    	+restOrderVO.getOrdertime()+
						       "&ordermemo="   		+restOrderVO.getOrdermemo();
				String url = "restordercmsupdate.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer orderid = Integer.valueOf(req.getParameter("orderid").trim());
				
				
//				Integer userid = null;
//				try {
//					userid = Integer.valueOf(req.getParameter("userid").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.put("userid","會員編號請填數字");
//				}
				
				Integer userid = null;
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof UserPrincipal) {
				    userid = ((UserPrincipal) principal).getUservo().getUserid();
				}
				
				Integer restid = null;
				try {
					restid = Integer.valueOf(req.getParameter("restid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("restid","餐廳編號請填數字");
				}
				
				Integer orderstatusid = null;
				try {
					orderstatusid = Integer.valueOf(req.getParameter("orderstatusid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("orderstatusid","訂單狀態編號請填數字");
				}
				
				java.sql.Timestamp ordertime = null;
				try {
					ordertime = java.sql.Timestamp.valueOf(req.getParameter("ordertime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("ordertime","請輸入日期時間");
				}
				
				String ordermemo = req.getParameter("ordermemo").trim();
				if (ordermemo == null || ordermemo.trim().length() == 0) {
					errorMsgs.put("ordermemo","訂單明細請勿空白");
				}
							

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restordercmsupdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RestOrderService restOrderSvc = new RestOrderService();
				RestOrderVO restOrderVO = restOrderSvc.updateRestorder(orderid, userid, restid, orderstatusid, ordertime, ordermemo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderVO", restOrderVO); // 資料庫update成功後,正確的的restOrderVO物件,存入req
				String url = "restordercmslistall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			
			
//			UserVO uservo = null;
//			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			
//			if (principal instanceof UserPrincipal) {
//				 uservo = ((UserPrincipal) principal).getUservo();
//			}
			
			Integer userid = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserPrincipal) {
			    userid = ((UserPrincipal) principal).getUservo().getUserid();
			}else if (userid == null) {
				res.sendRedirect("/login");
			}
			
//			System.err.println(uservo.getUserid());
			System.err.println(userid);
			
//			UserService userSvc = new UserService();
//			UserVO userVO = userSvc.getOneUser(3);
//			
//			Integer userid= uservo.getUserid();
			
			
			Integer restid = null;
			try {
				restid = Integer.valueOf(req.getParameter("restid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("restid","餐廳編號請填數字");
			}

			//取得餐廳名稱
			RestService restSvc = new RestService();
			RestVO restVO = restSvc.getOneRest(restid);
			String restname = restVO.getRestname();
			
//			Integer orderstatusid = null;
//			try {
//				orderstatusid = Integer.valueOf(req.getParameter("orderstatusid").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("orderstatusid","訂單狀態編號請填數字");
//			}

			//取得訂單狀態
			RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
			RestOrderStatusVO restOrderStatusVO = restOrderStatusSvc.getOneRestorderstatus(1);
			Integer orderstatusid= restOrderStatusVO.getOrderstatusid();
			String orderstatus = restOrderStatusVO.getOrderstatus();
			
			java.sql.Timestamp ordertime = null;
			try {
				ordertime = java.sql.Timestamp.valueOf(req.getParameter("ordertime").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("ordertime","請輸入日期時間");
			}
			
			String ordermemo = req.getParameter("ordermemo").trim();
			if (ordermemo == null || ordermemo.trim().length() == 0) {
				errorMsgs.put("ordermemo","訂單明細請勿空白");
			}
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restordercmsadd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				//訂單存到資料庫
				RestOrderService restOrderSvc = new RestOrderService();
				RestOrderVO restOrderVO = restOrderSvc.addRestorder(userid, restid, orderstatusid, ordertime, ordermemo);
//				uservo.getUserid()
//				RestOrderVO restOrderVO = restOrderSvc.insertWithRestOrderList(RestOrderVO restOrderVO,  List<RestOrderListVO> list);
//				
			
				//訂單明細取值
			    Integer orderid= restOrderVO.getOrderid();
			    
			    
//			    System.err.println("order_id = " + orderid);
//				Integer dishid= Integer.valueOf(req.getParameter("dishid").trim());
//				Integer dishprice= Integer.valueOf(req.getParameter("dishprice").trim());
//				Integer dishqty= Integer.valueOf(req.getParameter("dishqty").trim());
		
				
				//訂單明細存到資料庫

			    HttpSession session = req.getSession();
				List<DishVO> buylist = (Vector<DishVO>) session.getAttribute("shoppingcart");
//				System.err.println("buylist = " + buylist);
				for (int index = 0; index < buylist.size(); index++) {
//					System.err.println(buylist.get(index).getDishid());
				RestOrderListService restOrderListSvc = new RestOrderListService();
				RestOrderListVO restOrderListVO = restOrderListSvc.addRestorderlist(orderid, buylist.get(index).getDishid(),buylist.get(index).getDishprice(),buylist.get(index).getDishity());
				
				}
				
				
//				RestOrderListVO restOrderListVO = restOrderListSvc.insert2(RestOrderListVO restOrderListVO, java.sql.Connection con);
				
				
				//總價計算
				double total = buylist.stream()
						  .mapToDouble(o -> o.getDishprice() * o.getDishity())
						  .sum();
	
	
				String amount = String.valueOf(total);
				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "restorderfront.jsp";
				req.setAttribute("userid", userid);
				req.setAttribute("restname", restname);
				req.setAttribute("orderstatus", orderstatus);
				req.setAttribute("amount", amount);
				String url = "/restorderfront.jsp?orderid="+orderid;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer orderid = Integer.valueOf(req.getParameter("orderid"));
				
				/***************************2.開始刪除資料***************************************/
				RestOrderService restOrderSvc = new RestOrderService();
				restOrderSvc.deleteRestorder(orderid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "restordercmslistall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
