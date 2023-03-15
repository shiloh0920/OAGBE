<%@page import="com.tibame.tga105.rest.dishmodel.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.rest.restordermodel.*"%>
<%@page import="com.tibame.tga105.rest.restorderlistmodel.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>



<%
//     RestOrderListService restOrderListSvc = new RestOrderListService();
//     List<RestOrderListVO> list = restOrderListSvc.getAll();
//     pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
    <meta name="generator" content="Hugo 0.108.0" />
    <title>OGABE|訂單與訂單明細</title>
    <link href="dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="dist/css/carousel.css" rel="stylesheet" />
    <link href="dist/css/my.css" rel="stylesheet" />
    <!-- <style>
      footer {
         position: absolute;
         bottom: 0%;
       }
    </style> -->
  </head>

  <body>
  
    	<%@ include file="header.jsp" %>
    	
    <main>
      <!-- go to top -->
      <div class="to-top">
        <a href="#">TOP</a>
      </div>
     <div class="container-fluid pt-5 px-5">
        <h1 class="h_center">已收到您的訂單</h1>
      </div>
      <hr />
      <h2 class="h_center">訂單資訊</h2>
     <table class="table ">
        <thead>
          <tr>
           <th class="col rest_dish_select_qty">訂單編號</th>
            <th class="col rest_dish_select_qty">會員編號</th>
            <th class="col rest_dish_select_qty">餐廳</th>
     		<th class="col rest_dish_select_qty">訂單狀態</th>
     		<th class="col rest_dish_select_qty">訂單備註</th>
			<th class="col rest_dish_select_qty">預定取餐時間</th>    	
          </tr>
        </thead >
        <tbody>
<%--         <c:forEach var="restOrderVO" items="${list}" > --%>
          <tr>
            <td>${param.orderid}</td>
            <td>${userid}</td>
            <td>${restname}</td>        
            <td>${orderstatus}</td>
            <td>${param.ordermemo}</td>
            <td>${param.ordertime}</td>
      </tr>
<%--          </c:forEach> --%>
       </tbody>
      </table>
      
      <div class="container-fluid pt-5 px-5">  
      <h2 class="h_center">這是您的訂購項目</h2>      
      </div>
	    <table class="table ">
         <thead>
         <tr>
				<th class="col-2 rest_dish_select_qty">品名</th>
				<th class="col-2 rest_dish_select_qty">餐點價格</th>
				<th class="col-1 rest_dish_select_qty">餐點數量</th>
         </tr>
         </thead>
        <tbody>
				<%
				@SuppressWarnings("unchecked")
				Vector<DishVO> buylist = (Vector<DishVO>) session.getAttribute("shoppingcart");
				String amount = (String) request.getAttribute("amount");
				%>
				<%
				for (int i = 0; i < buylist.size(); i++) {
					DishVO order = buylist.get(i);
					String dishname = order.getDishname();
					Integer dishprice = order.getDishprice();
					Integer dishity = order.getDishity();
				%>
				<tr>
					<td class="col-2 "><%=dishname%></td>
					<td class="col-2 ">$ <%=dishprice%></td>
					<td class="col-1 "><%=dishity%></td>
				</tr>

				<%
				}
				%>
		</tbody>
		</table>
         <table>
<!-- 				<hr size="1" noshade="noshade" style="border:1px #cccccc dotted;"/> -->
				<tr>
					<td class="col"></td>
					<td class="col rest_dish_select_price_color ">總金額:$<%=amount%></td>
					<td class="col height-20 rest_dish_select_price_color"></td>
				</tr>
		</table>
     
      <hr class="featurette-divider" />
      
        <h2 class="text-center">付款方式</h2>
    <div class="text-center">
        <a class="btn btn-warning" href="restorderlistfront.jsp">現場付款</a>
        <a class="btn btn-outline-warning" href="restorderlistfront.jsp">信用卡付款</a>
    </div>
    
<!--       <hr class="featurette-divider" /> -->
     </main>
  
    <%@ include file="footer.jsp" %>
    
    <script src="dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
