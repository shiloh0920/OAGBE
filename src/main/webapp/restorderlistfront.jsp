<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.rest.restorderlistmodel.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
RestOrderListService restOrderListSvc = new RestOrderListService();
List<RestOrderListVO> list = restOrderListSvc.getAll();
pageContext.setAttribute("list", list);
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
<title>OGABE|訂餐成功</title>
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
		<div class="container-fluid pt-5 px-5 h_center">
				<div class="bg-lightblue">
					<div
						class="form-label rest_dish_select_qty rest_dish_select_word_color">
						<div class="h_center">
							<img src="images\emojione_checks.jpg"
								class="figure-img img-fluid rounded" alt="check" />
						</div>
						<h1 class="fw-normal">恭喜訂餐完成!</h1>
					</div>
					<br />
					<div class="form-label rest_dish_select_qty text-center">
						<label>感謝您的訂餐，<br/>餐點將盡速為您製作!
						</label>
					</div>
					<br />
					<div class="form-label rest_dish_select_qty text-center">
						<label>並請於預定時段至餐廳取餐!</label>
					</div>
					<br />
					<div class="text-center">
						<a class="btn btn-outline-secondary" href="#">回首頁</a> 
						<a class="btn btn-outline-secondary" href="/userpage">會員中心</a>
					</div>
				</div>
			</div>
			<hr class="featurette-divider" />
	</main>
	
	<%@ include file="footer.jsp" %>
	
	<script src="dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
