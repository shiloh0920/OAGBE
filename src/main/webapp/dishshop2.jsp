<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.rest.dishmodel.*"%>
<%@ page import="com.tibame.tga105.rest.restorderlistmodel.*"%>
<%@page import="com.tibame.tga105.rest.restmodel.RestService"%>

<%
RestService restSvc = new RestService();
String restid = request.getParameter("restid");
// pageContext.setAttribute("restid",restid);
Set<DishVO> set = restSvc.getDishsByRestid(Integer.valueOf(restid));
pageContext.setAttribute("set", set);
%>

<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.108.0" />
<title>OGABE|點餐畫面</title>
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
		
<!-- 		<hr class="featurette-divider" /> -->
		<br>
		<div class="container marketing">
			<h1 class="rest_dish_select_word_color h_center">餐點選擇:</h1>

			<div class="row">
				<c:forEach var="dishVO" items="${set}">
					<div class="col-lg-3 text-center">
						<form name="shoppingForm" action="Shopping" method="POST">
							<div>

								<p>
									<img class="figure-img img-fluid rounded"
										src="DishServlet?dishid=${dishVO.dishid}">
								</p>

								<h2 class="fw-normal">${dishVO.dishname}</h2>
								<p class="height-80">${dishVO.dishdescription}</p>
								<p class="rest_dish_select_qty">
									數量: <label for="InputDishity" class="form-label"> <input
										type="text" class="form-control" name="dishity" size="3"
										id="InputDishity" value=1>
									</label>
								</p>
								<p class="height-20 rest_dish_select_price_color">單價: NT$
									${dishVO.dishprice}</p>
								<p>
									<input type="submit" class="btn rest-btn-primary" id="cartadd"
										value="加入購物車">
								</p>
							</div>
							<input type="hidden" name="dishid" value="${dishVO.dishid}">
							<input type="hidden" name="dishname" value="${dishVO.dishname}">
							<input type="hidden" name="dishspec" value="${dishVO.dishspec}">
							<input type="hidden" name="dishprice" value="${dishVO.dishprice}">
							<input type="hidden" name="restid" value="<%=restid%>">   <!--line 11-->
							<input type="hidden" name="action" value="ADD">
						</form>
					</div>
				</c:forEach>

				<p>
					<jsp:include page="/dishcart.jsp" flush="true" />
<!-- 				<hr class="featurette-divider" /> -->
	</main>

	<%@ include file="footer.jsp" %>
	
	<script src="dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
