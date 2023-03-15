<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.rest.restmodel.*"%>


<%
RestService restSvc = new RestService();
List<RestVO> list = restSvc.getAll();
pageContext.setAttribute("list", list);
%>

<%
   RestVO restVO = (RestVO) request.getAttribute("restVO");
%>

<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.108.0" />
<title>OGABE|點餐首頁</title>
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
			<h1 class="h_center">餐廳選擇</h1>
		</div>
		<hr />
		<c:forEach var="restVO" items="${list}">
		<div class="row featurette h_center">			
				<div class="card mb-3" style="max-width: 540px;">
					<div class="row g-0">
						<div class="col-md-4 h_center">
							<img class="figure-img img-fluid rounded"
								src="RestServlet?restid=${restVO.restid}">
						</div>
						<div class="col-md-8">
							<div class="card-body text-center">
								<h5 class="card-title">餐廳: ${restVO.restname}</h5>
								<p class="card-text">地址: ${restVO.restaddress}</p>
								<a class="btn rest-btn-primary" href="dishshop2.jsp?restid=${restVO.restid}">線上點餐</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
<!-- 		<hr class="featurette-divider" /> -->
	</main>
	
	<%@ include file="footer.jsp" %>
	
	<script src="dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
