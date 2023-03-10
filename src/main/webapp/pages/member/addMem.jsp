<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.mem.model.*"%>

<%
//MemServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<%-- --<%=memVO == null%>--${memVO.memId}-- --%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>會員註冊</title>

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
.sidenav {
	width: 130px;
	position: sticky;
	z-index: 1;
	top: 110px;
	left: 10px;
	background: rgb(255, 255, 255);
	overflow-x: hidden;
	padding: 8px 0;
}

.sidenav a {
	padding: 6px 8px 6px 16px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
}

.sidenav a:hover {
	color: #f1f1f1;
}

.memlistonebox {
	margin-top: 5%;
	margin-left: 25%;
}

table {
	width: 500px;
	background-color: white;
}

th, td {
	padding: 5px;
	text-align: center;
	color: #646e78;
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
	<div>
		<div style="display: flex; flex-direction: row">
			<div class="sidenav">
				<!-- 				<a href="#contact">帳號設定</a> <a href="#oreder_search">訂單查詢</a> <a -->
				<!-- 					href="#goodsadd">商品收藏</a> <a href="#roomadd">房型收藏</a> <a -->
				<!-- 					href="#sponsoradd">募資收藏</a> <a href="#notifymailbox">通知信箱</a> -->
			</div>


			<div class="col text-center">
				<div class="memlistonebox">
					<p style="font-size: 32px; margin-right: 50%; margin-bottom: 2%;">會員註冊</p>
					<div style="margin-right: 45%;">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>

					<FORM METHOD="post" ACTION="/molife/memberController" name="memRegistery"
						enctype="multipart/form-data">
						<table>
							<tr>
								<td>姓氏:</td>
								<td><input type="TEXT" name="memLname" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemLname()%>" /></td>
							</tr>
							<tr>
								<td>名稱:</td>
								<td><input type="TEXT" name="memFname" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemFname()%>" /></td>
							</tr>
							<tr>
								<td>暱稱:</td>
								<td><input type="TEXT" name="memNickname" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemNickname()%>" /></td>
							</tr>
							<tr>
								<td>信箱:</td>
								<td><input type="TEXT" name="memEmail" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemEmail()%>" /></td>
							</tr>
							<tr>
								<td>密碼:</td>
								<td><input type="TEXT" name="memPsd" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemPsd()%>" /></td>
							</tr>
							<tr>
								<td>確認密碼:</td>
								<td><input type="TEXT" name="memPsd" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemPsd()%>" /></td>
							</tr>
							<tr>
								<td>手機:</td>
								<td><input type="TEXT" name="memPhone" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemPhone()%>" /></td>
							</tr>
							<tr>
								<td>居住地址:</td>
								<td><input type="TEXT" name="memAddress" size="30"
									value="<%=(memVO == null) ? "" : memVO.getMemAddress()%>" /></td>
							</tr>
							<tr>
								<td>頭像圖片上傳:</td>
								<td><input type="file" name="memPicId" id="memPicId"
									style="margin-left: 10%; margin-top: 3%"></td>
							</tr>

							<jsp:useBean id="memtSvc" scope="page"
								class="com.tibame.tga105.mem.model.MemService" />
						</table>
						<br> <input type="hidden" name="action" value="insert">
						 <input
							class="btn btn-primary update_button" type="submit" value="確認註冊"
							style="background-color: #a7754d; padding: 12px 50px; margin-bottom: 4%; margin-left: -50%;">
					</FORM>


				</div>
			</div>
		</div>
	</div>

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
