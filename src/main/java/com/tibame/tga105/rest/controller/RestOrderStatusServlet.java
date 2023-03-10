package com.tibame.tga105.rest.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusService;
import com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusVO;


public class RestOrderStatusServlet extends HttpServlet {
	
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
				String str = req.getParameter("orderstatusid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("orderstatusid","請輸入訂單狀態編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer orderstatusid = null;
				try {
					orderstatusid = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("orderstatusid","訂單狀態編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
				RestOrderStatusVO restOrderStatusVO = restOrderStatusSvc.getOneRestorderstatus(orderstatusid);
				if (restOrderStatusVO == null) {
					errorMsgs.put("orderstatusid","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderStatusVO", restOrderStatusVO); // 資料庫取出的orderstatusidVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer orderstatusid = Integer.valueOf(req.getParameter("orderstatusid"));
				
				/***************************2.開始查詢資料****************************************/
				RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
				RestOrderStatusVO restOrderStatusVO = restOrderStatusSvc.getOneRestorderstatus(orderstatusid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?orderstatusid="  +restOrderStatusVO.getOrderstatusid()+
						       "&orderstatus="  	+restOrderStatusVO.getOrderstatus();
				String url = "/emp/update_emp_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer orderstatusid = Integer.valueOf(req.getParameter("orderstatusid").trim());
				
								
				String orderstatus = req.getParameter("orderstatus").trim();
				if (orderstatus == null || orderstatus.trim().length() == 0) {
					errorMsgs.put("orderstatus","訂單狀態請勿空白");
				}
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
				RestOrderStatusVO restOrderStatusVO = restOrderStatusSvc.updateRestorderstatus(orderstatusid, orderstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restOrderStatusVO", restOrderStatusVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String orderstatus = req.getParameter("orderstatus").trim();
			if (orderstatus == null || orderstatus.trim().length() == 0) {
				errorMsgs.put("orderstatus","訂單狀態請勿空白");
			}
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
				restOrderStatusSvc.addRestorderstatus(orderstatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer orderstatusid = Integer.valueOf(req.getParameter("orderstatusid"));
				
				/***************************2.開始刪除資料***************************************/
				RestOrderStatusService restOrderStatusSvc = new RestOrderStatusService();
				restOrderStatusSvc.deleteRestorderstatus(orderstatusid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}

}
