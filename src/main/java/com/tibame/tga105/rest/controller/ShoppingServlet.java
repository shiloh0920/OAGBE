package com.tibame.tga105.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.tga105.rest.dishmodel.DishVO;
@WebServlet("/Shopping")
public class ShoppingServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		List<DishVO> buylist = (Vector<DishVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的餐點
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				System.out.println("del="+del);
//System.out.println("--------------------------------buylist.size()="+buylist.size());
				int d = Integer.parseInt(del);
				buylist.remove(d);
//System.out.println("--------------------------------buylist.size()="+buylist.size());
			}
			// 新增餐點至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的餐點
				DishVO adishVO = getDishVO(req);

				if (buylist == null) {
					buylist = new Vector<DishVO>();
					buylist.add(adishVO);
				} else {
					if (buylist.contains(adishVO)) {
						DishVO innerDishVO = buylist.get(buylist.indexOf(adishVO));
						innerDishVO.setDishity(innerDishVO.getDishity() + adishVO.getDishity());
					} else {
						buylist.add(adishVO);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
//			String url = "/dishshop.jsp";
			String restid = req.getParameter("restid");
			String url = "/dishshop2.jsp?restid="+restid;
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車餐點價錢總數
		else if (action.equals("CHECKOUT")) {
//			double total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				DishVO order = buylist.get(i);
//				Integer dishprice = order.getDishprice();
//				Integer dishity = order.getDishity();
//				total += (dishprice * dishity);
//			}
			double total = buylist.stream()
								  .mapToDouble(o -> o.getDishprice() * o.getDishity())
								  .sum();
			
			
			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			String url = "/dishcheckout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	
	}

	private DishVO getDishVO(HttpServletRequest req) {

		String dishid = req.getParameter("dishid");
		String dishity = req.getParameter("dishity");
		String dishname = req.getParameter("dishname");
		String dishdescription = req.getParameter("dishdescription");
		String dishspec = req.getParameter("dishspec");
		String dishprice = req.getParameter("dishprice");

		DishVO dishVO = new DishVO();

		dishVO.setDishid(new Integer(dishid));
		dishVO.setDishname(dishname);
		dishVO.setDishdescription(dishdescription);
		dishVO.setDishspec(dishspec);
		dishVO.setDishprice(new Integer(dishprice));
		dishVO.setDishity((new Integer(dishity)).intValue());
		return dishVO;
	}

}
