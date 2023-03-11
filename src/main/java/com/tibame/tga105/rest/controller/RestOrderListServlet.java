package com.tibame.tga105.rest.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga105.rest.restorderlistmodel.RestOrderListService;
import com.tibame.tga105.rest.restorderlistmodel.RestOrderListVO;

@WebServlet("/restOrderList.do")
public class RestOrderListServlet extends HttpServlet {

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
				String str = req.getParameter("orderlistid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("orderlistid","請輸入訂單明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restorderlistcmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer orderlistid = null;
				try {
					orderlistid = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("orderlistid","訂單明細格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restorderlistcmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RestOrderListService restOrderListSvc = new RestOrderListService();
				RestOrderListVO restOrderListVO = restOrderListSvc.getOneRestorderlist(orderlistid);
				if (restOrderListVO == null) {
					errorMsgs.put("orderlistid","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restorderlistcmsselect.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderListVO", restOrderListVO); // 資料庫取出的restOrderListVO物件,存入req
				String url = "restorderlistcmslistone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer orderlistid = Integer.valueOf(req.getParameter("orderlistid"));
				
				/***************************2.開始查詢資料****************************************/
				RestOrderListService restOrderListSvc = new RestOrderListService();
				RestOrderListVO restOrderListVO = restOrderListSvc.getOneRestorderlist(orderlistid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?orderlistid="  +restOrderListVO.getOrderlistid()+
						       "&orderid="  	+restOrderListVO.getOrderid()+
						       "&dishid="    	+restOrderListVO.getDishid()+
						       "&dishprice="	+restOrderListVO.getDishprice()+
						       "&dishqty="    	+restOrderListVO.getDishqty();
				String url = "restorderlistcmsupdate.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 restorderlistcmsupdate.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer orderlistid = Integer.valueOf(req.getParameter("orderlistid").trim());
							
				
				Integer orderid = null;
				try {
					orderid = Integer.valueOf(req.getParameter("orderid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("orderid","訂單編號請填數字");
				}
				
			
				Integer dishid = null;
				try {
					dishid = Integer.valueOf(req.getParameter("dishid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("dishid","餐點編號請填數字");
				}
				
				Integer dishprice = null;
				try {
					dishprice = Integer.valueOf(req.getParameter("dishprice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("dishprice","餐點價格請填數字");
				}
				
				Integer dishqty = null;
				try {
					dishqty = Integer.valueOf(req.getParameter("dishqty").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("dishqty","餐點數量請填數字");
				}
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restorderlistcmsupdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RestOrderListService restOrderListSvc = new RestOrderListService();
				RestOrderListVO restOrderListVO = restOrderListSvc.updateRestorderlist(orderlistid, orderid, dishid, dishprice, dishqty);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderListVO", restOrderListVO); // 資料庫update成功後,正確的的restOrderListVO物件,存入req
				String url = "restorderlistcmslistall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer orderid = null;
			try {
				orderid = Integer.valueOf(req.getParameter("orderid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("orderid","訂單編號請填數字");
			}
			
		
			Integer dishid = null;
			try {
				dishid = Integer.valueOf(req.getParameter("dishid").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("dishid","餐點編號請填數字");
			}
			
			Integer dishprice = null;
			try {
				dishprice = Integer.valueOf(req.getParameter("dishprice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("dishprice","餐點價格請填數字");
			}
			
			Integer dishqty = null;
			try {
				dishqty = Integer.valueOf(req.getParameter("dishqty").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("dishqty","餐點數量請填數字");
			}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("restorderlistcmsadd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RestOrderListService restOrderListSvc = new RestOrderListService();
				restOrderListSvc.addRestorderlist(orderid, dishid, dishprice, dishqty);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "restorderlistcmslistall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer orderlistid = Integer.valueOf(req.getParameter("orderlistid"));
				
				/***************************2.開始刪除資料***************************************/
				RestOrderListService restOrderListSvc = new RestOrderListService();
				restOrderListSvc.deleteRestorderlist(orderlistid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "restorderlistcmslistall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
