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

import com.tibame.tga105.rest.dishmodel.DishDAO;
import com.tibame.tga105.rest.dishmodel.DishService;
import com.tibame.tga105.rest.dishmodel.DishVO;

@MultipartConfig
@WebServlet(urlPatterns = { "/DishServlet", "/dish.do" })
public class DishServlet extends HttpServlet {

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int dishid = Integer.parseInt(req.getParameter("dishid"));
		DishDAO dishDAO = new DishDAO();

		try {
			DishVO dishVO = dishDAO.getDishById(dishid);
			res.setContentType("image/jpeg");
			OutputStream out = res.getOutputStream();
			out.write(dishVO.getDishimg());
			out.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		Part filePart = req.getPart("dishimg");
//      InputStream fileContent = filePart.getInputStream();
//      ByteArrayOutputStream output = new ByteArrayOutputStream();
//      byte[] buffer = new byte[4096];
//      int n = 0;
//      while (-1 != (n = fileContent.read(buffer))) {
//          output.write(buffer, 0, n);
//      }
//      byte[] bytes = output.toByteArray();

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("dishid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入餐點編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dishcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer dishid = null;
			try {
				dishid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("餐點編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dishcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			DishService dishSvc = new DishService();
			DishVO dishVO = dishSvc.getOneDish(dishid);
			if (dishVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dishcmsselect.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("dishVO", dishVO); // 資料庫取出的dishVO物件,存入req
			String url = "/dishcmslistone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer dishid = Integer.valueOf(req.getParameter("dishid"));

			/*************************** 2.開始查詢資料 ****************************************/
			DishService dishSvc = new DishService();
			DishVO dishVO = dishSvc.getOneDish(dishid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("dishVO", dishVO); // 資料庫取出的dishVO物件,存入req
			String url = "/dishcmsupdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer dishid = Integer.valueOf(req.getParameter("dishid").trim());

			Integer restid = Integer.valueOf(req.getParameter("restid").trim());
			if (restid == null) {
				errorMsgs.add("餐廳編號請勿空白");
			}

//			String dishname = req.getParameter("dishname");
//			String dishnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (dishname == null || dishname.trim().length() == 0) {
//				errorMsgs.add("餐點名稱: 請勿空白");
//			} else if (!dishname.trim().matches(dishnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			String dishname = req.getParameter("dishname").trim();
			if (dishname == null || dishname.trim().length() == 0) {
				errorMsgs.add("餐點名稱請勿空白");
			}
			
			Integer dishprice = Integer.valueOf(req.getParameter("dishprice").trim());
			if (dishprice == null) {
				errorMsgs.add("餐點價格請勿空白");
			}

			Integer dishity = Integer.valueOf(req.getParameter("dishity").trim());
			if (dishity == null) {
				errorMsgs.add("餐點數量請勿空白");
			}

			String dishdescription = req.getParameter("dishdescription").trim();
			if (dishdescription == null || dishdescription.trim().length() == 0) {
				errorMsgs.add("請簡單描述餐點");
			}

			String dishspec = req.getParameter("dishspec").trim();
			if (dishspec == null || dishspec.trim().length() == 0) {
				errorMsgs.add("請輸入餐點明細");
			}

			String savepath = req.getServletContext().getRealPath("/DB-image");
			File imgfolderPath = new File(savepath);
			if (!imgfolderPath.exists()) {
				imgfolderPath.mkdirs();
			}
			javax.servlet.http.Part part = req.getPart("dishimg");
			String filename = part.getSubmittedFileName();
			byte[] dishimg = null;
			if (filename != null && !filename.isEmpty()) {
				String imgPath = imgfolderPath + "/" + filename;
				part.write(imgPath);
				dishimg = getPictureByteArray(imgPath);
			}		
			 else {
			    
			    errorMsgs.add("圖片未上傳");
			}
			

//			byte[] dishimg= req.getServletContext("dishimg");

			DishVO dishVO = new DishVO();
			dishVO.setDishid(dishid);
			dishVO.setRestid(restid);
			dishVO.setDishname(dishname);
			dishVO.setDishprice(dishprice);
			dishVO.setDishity(dishity);
			dishVO.setDishdescription(dishdescription);
			dishVO.setDishspec(dishspec);
			dishVO.setDishimg(dishimg);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的dishVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/dishcmsupdate.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			DishService dishSvc = new DishService();
			dishVO = dishSvc.updateDish(dishid, restid, dishname, dishprice, dishity, dishdescription, dishspec,
					dishimg);
			// dishimg還沒加入
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("dishVO", dishVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/dishcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer restid = Integer.valueOf(req.getParameter("restid").trim());
			if (restid == null) {
				errorMsgs.add("餐廳編號請勿空白");
			}

//			String dishname = req.getParameter("dishname");
//			String dishnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (dishname == null || dishname.trim().length() == 0) {
//				errorMsgs.add("餐點名稱: 請勿空白");
//			} else if (!dishname.trim().matches(dishnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}

			
			String dishname = req.getParameter("dishname").trim();
			if (dishname == null || dishname.trim().length() == 0) {
				errorMsgs.add("餐點名稱請勿空白");
			}

			Integer dishprice = null;
			try {
				dishprice = Integer.valueOf(req.getParameter("dishprice").trim());
			} catch (NumberFormatException e) {
				dishprice = 0;
				errorMsgs.add("餐點價格請勿空白");
			}
			
			Integer dishity = null;
			try {
				dishity = Integer.valueOf(req.getParameter("dishity").trim());
			} catch (NumberFormatException e) {
				dishity = 0;
				errorMsgs.add("餐點數量請勿空白");
			}
	
			String dishdescription = req.getParameter("dishdescription").trim();
			if (dishdescription == null || dishdescription.trim().length() == 0) {
				errorMsgs.add("請簡單描述餐點");
			}

			String dishspec = req.getParameter("dishspec").trim();
			if (dishspec == null || dishspec.trim().length() == 0) {
				errorMsgs.add("請輸入餐點明細");
			}

//			byte[] dishimg= req.getServletContext("dishimg");

			String savepath = req.getServletContext().getRealPath("/DB-image");
			File imgfolderPath = new File(savepath);
			if (!imgfolderPath.exists()) {
				imgfolderPath.mkdirs();
			}
			javax.servlet.http.Part part = req.getPart("dishimg");
			String filename = part.getSubmittedFileName();
			byte[] dishimg = null;
			if (filename != null && !filename.isEmpty()) {
				String imgPath = imgfolderPath + "/" + filename;
				part.write(imgPath);
				dishimg = getPictureByteArray(imgPath);
			}		
			 else {
			    
			    errorMsgs.add("圖片未上傳");
			}

			DishVO dishVO = new DishVO();
			dishVO.setRestid(restid);
			dishVO.setDishname(dishname);
			dishVO.setDishprice(dishprice);
			dishVO.setDishity(dishity);
			dishVO.setDishdescription(dishdescription);
			dishVO.setDishspec(dishspec);
			dishVO.setDishimg(dishimg);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的diahVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/dishcmsadd.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			DishService dishSvc = new DishService();
			dishVO = dishSvc.addDish(restid, dishname, dishprice, dishity, dishdescription, dishspec, dishimg);
			// dishimg還沒加入
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dishcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer dishid = Integer.valueOf(req.getParameter("dishid"));

			/*************************** 2.開始刪除資料 ***************************************/
			DishService dishSvc = new DishService();
			dishSvc.deleteDish(dishid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dishcmslistall.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
