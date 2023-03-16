<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tibame.tga105.rest.dishmodel.*"%>
<%
DishVO dishVO = (DishVO) request.getAttribute("dishVO");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.108.0" />
<title>OGABE|點餐後台</title>
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

	<%@ include file="cmsheader.jsp" %>
	
	<main>
		<!-- go to top -->
		<div class="to-top">
			<a href="#">TOP</a>
		</div>
		<!-- Modal註冊彈跳視窗 -->
		
		<!-- model end -->
		<div class="container-fluid pt-5 px-5">
			<h1 class="h_center">點餐系統後台</h1>
		</div>
		<hr />
		<h2 class="h_center">餐點管理</h2>

		<table class="table table-bordered">

			<tr>
				<th>餐點編號</th>
				<th>餐廳</th>
				<th>餐點名稱</th>
				<th>餐點價格</th>
				<th>餐點數量</th>
				<th>餐點描述</th>
				<th>餐點明細</th>
				<th>餐點圖片</th>
			</tr>
			<tr>
				<td>${dishVO.dishid}</td>
				<td>${dishVO.restid}-[${dishVO.restVO.restname}]</td>
				<td>${dishVO.dishname}</td>
				<td>${dishVO.dishprice}</td>
				<td>${dishVO.dishity}</td>
				<td>${dishVO.dishdescription}</td>
				<td>${dishVO.dishspec}</td>
				<td><img class="figure-img img-fluid rounded"
					src="DishServlet?dishid=${dishVO.dishid}"></td>
			</tr>

		</table>
		<div class="h_center">
			<a class="btn btn-outline-secondary" href="dishcmslistall.jsp">餐點管理</a>
		</div>
		<hr class="featurette-divider" />
	</main>
	<!-- footer -->
	
	<%@ include file="cmsfooter.jsp" %>
	
	<script src="dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
