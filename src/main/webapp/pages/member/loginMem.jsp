<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.tibame.tga105.mem.model.MemVO"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
%>

<%-- <% memVO memVO = (memVO) request.getAttribute("memVO");%>> --%>

--<%=memVO == null%>--${memVO.memId}--


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>會員登入</title>

<!--index_css-->
<link rel="stylesheet" type="text/css" href="N_1_index.css" />

<!--Bootstrap導入程式-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
	crossorigin="anonymous"></script>

<!-- JQuery導入 -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<!-- fontawesome導入 -->
<script src="https://kit.fontawesome.com/793f12a2cf.js"
	crossorigin="anonymous"></script>

<style>
div.login-box {
	margin: 150px 450px 10px 450px;
}

p.login-box-msg {
	font-size: 32px;
}

.login_button {
	position: relative;
}

div.btn_click {
	margin-top: 20px;
	margin-bottom: 20px;
}
</style>
</head>

<body>
	<!-- Header -->
	<div id="header"></div>

	<!-- Main Navigation -->
	<div class="main_nav_container bg-yellow">
		<div>
			<div class="row">
				<div class="col-lg-12 text-right">
					<div class="logo_container">
						<a href="#"> <img src="../../images/home/logoMoLife.png" />
						</a>
					</div>
					<nav class="navbar">
						<ul class="navbar_menu">
							<li><a href="#">寵物旅館</a></li>
							<li><a href="#">商品專區</a></li>
							<li><a href="#">寵物募款</a></li>
							<li><a href="#">寵物論壇</a></li>
							<li><a href="#">聯絡我們</a></li>
							<li><a href="#">管理員後台</a></li>
						</ul>
						<ul class="navbar_user">
							<a href="#"><img class="icon" src="../../images/home/car.png" /></a>
							<a href="#"><img class="icon"
								src="../../images/home/ring.png" /></a>
							<a href="#"><img class="icon" src="../../images/home/man.png" /></a>
						</ul>
						<div class="hamburger_container">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>



	<div class="login-box">

		<div class="col
	text-center">
			<p class="login-box-msg">會員登入</p>
		</div>
		<div style= "margin-left:40%">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<form METHOD="post" ACTION="/TGA-105-MoLife/memberController" name="login"
			enctype="multipart/form-data">
			<div class="col text-center">
				<div class="mail.text">
					<label for="exampleInputEmail1" style="color: #8d98a7;">信箱</label>
					<input type="TEXT" name="memEmail" size="30" /> <br /> <br />
				</div>
				<div class="psd.text">
					<label for="exampleInputPassword1" style="color: #8d98a7;">密碼</label>
					<input type="TEXT" name="memPsd" size="30" />
				</div>
			</div>

			<jsp:useBean id="memSvc" scope="page"
				class="com.tibame.tga105.mem.model.MemService" />

			<div class="btn_click col text-center">
				<a href="" style="color: #EAB464; margin-right: 50px">忘記密碼</a><a
					href="addMem.jsp" style="color: #EAB464">我要註冊</a>
			</div>
	</div>
	<div>
		<div class="col text-center">
			<input type="hidden" name="action" value="login"> <input
				class="btn btn-primary login_button" type="submit" value="登入"
				style="background-color: #A7754d; padding: 14px 100px;">
		</div>


			<li><a href='listAllMem.jsp'>List</a> all Member. <br> <br></li>

	</div>
	</form>
	</div>

	<br />
	<br />
	<br />
	<br />
	<br />

	<!-- Footer -->
	<!-- Footer_start -->
	<div id="footer"></div>
	<!-- Footer_end -->

	<!-- 引入 header / footer /scrollTop-->
	<script>
		$(function() {
			$("#header").load("../../header.html");
			$("#footer").load("../../footer.html");
			$("#scrollTop").load("../../scrollTop.html");
		});
	</script>
</body>
</html>
