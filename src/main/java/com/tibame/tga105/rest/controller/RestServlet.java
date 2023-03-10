package com.tibame.tga105.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.tga105.rest.restmodel.RestDAO;
import com.tibame.tga105.rest.restmodel.RestService;
import com.tibame.tga105.rest.restmodel.RestVO;

@MultipartConfig
@WebServlet(urlPatterns = { "/RestServlet", "/rest.do" })
public class RestServlet extends HttpServlet {

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int restid = Integer.parseInt(req.getParameter("restid"));
		RestDAO restDAO = new RestDAO();

		try {
			RestVO restVO = restDAO.getRestById(restid);
			res.setContentType("image/jpeg");
			OutputStream out = res.getOutputStream();
			out.write(restVO.getRestimg());
			out.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("restid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入餐廳編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/restcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer restid = null;
			try {
				restid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("餐廳編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/restcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RestService restSvc = new RestService();
			RestVO restVO = restSvc.getOneRest(restid);
			if (restVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/restcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("restVO", restVO); // 資料庫取出的restVO物件,存入req
			String url = "/restcmslistone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer restid = Integer.valueOf(req.getParameter("restid"));

			/*************************** 2.開始查詢資料 ****************************************/
			RestService restSvc = new RestService();
			RestVO restVO = restSvc.getOneRest(restid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("restVO", restVO); // 資料庫取出的restVO物件,存入req
			String url = "/restcmsupdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer restid = Integer.valueOf(req.getParameter("restid").trim());

//			String restname = req.getParameter("restname");
//			String restnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (restname == null || restname.trim().length() == 0) {
//				errorMsgs.add("餐廳名稱: 請勿空白");
//			} else if (!restname.trim().matches(restnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			String restname = req.getParameter("restname").trim();
			if (restname == null || restname.trim().length() == 0) {
				errorMsgs.add("餐廳名稱請勿空白");
			}
			
			String restaddress = req.getParameter("restaddress").trim();
			if (restaddress == null || restaddress.trim().length() == 0) {
				errorMsgs.add("餐廳地址請勿空白");
			}

			String savepath = req.getServletContext().getRealPath("/DB-image");
			File imgfolderPath = new File(savepath);
			if (!imgfolderPath.exists()) {
				imgfolderPath.mkdirs();
			}
			javax.servlet.http.Part part = req.getPart("restimg");
			String filename = part.getSubmittedFileName();
			byte[] restimg = null;
			if (filename != null && !filename.isEmpty()) {
				String imgPath = imgfolderPath + "/" + filename;
				part.write(imgPath);
				restimg = getPictureByteArray(imgPath);
			}		
			 else {
			    
			    errorMsgs.add("圖片未上傳");
			}

			RestVO restVO = new RestVO();
			restVO.setRestid(restid);
			restVO.setRestname(restname);
			restVO.setRestaddress(restaddress);
			restVO.setRestimg(restimg);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的restVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/restcmsupdate.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			RestService restSvc = new RestService();
			restVO = restSvc.updateRest(restid, restname, restaddress, restimg);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("restVO", restVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/restcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String restname = req.getParameter("restname");
//			String restnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (restname == null || restname.trim().length() == 0) {
//				errorMsgs.add("餐廳名稱: 請勿空白");
//			} else if (!restname.trim().matches(restnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐廳名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			String restname = req.getParameter("restname").trim();
			if (restname == null || restname.trim().length() == 0) {
				errorMsgs.add("餐廳名稱請勿空白");
			}
			
			String restaddress = req.getParameter("restaddress").trim();
			if (restaddress == null || restaddress.trim().length() == 0) {
				errorMsgs.add("餐廳地址請勿空白");
			}

			String savepath = req.getServletContext().getRealPath("/DB-image");
			File imgfolderPath = new File(savepath);
			if (!imgfolderPath.exists()) {
				imgfolderPath.mkdirs();
			}
			javax.servlet.http.Part part = req.getPart("restimg");
			String filename = part.getSubmittedFileName();
			byte[] restimg = null;
			if (filename != null && !filename.isEmpty()) {
				String imgPath = imgfolderPath + "/" + filename;
				part.write(imgPath);
				restimg = getPictureByteArray(imgPath);
			}		
			 else {
			    
			    errorMsgs.add("圖片未上傳");
			}

			RestVO restVO = new RestVO();

			restVO.setRestname(restname);
			restVO.setRestaddress(restaddress);
			restVO.setRestimg(restimg);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/restcmsadd.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			RestService restSvc = new RestService();
			restVO = restSvc.addRest(restname, restaddress, restimg);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/restcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer restid = Integer.valueOf(req.getParameter("restid"));

			/*************************** 2.開始刪除資料 ***************************************/
			RestService restSvc = new RestService();
			restSvc.deleteRest(restid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/restcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
