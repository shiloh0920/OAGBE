<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.rest.restordermodel.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>OGABE|後台餐廳首頁</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="/sidebar-01/css/style.css">
<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="dist/css/carousel.css" rel="stylesheet" />
<link href="dist/css/my.css" rel="stylesheet" />
<style>
body {
	padding-top: 0rem;
	padding-bottom: 0rem;
	color: #5a5a5a;
}
</style>
</head>

<body>
	<div class="wrapper d-flex align-items-stretch">
		<nav id="sidebar">
			<div class="p-4 pt-5">
				<img src="/sidebar-01/images/ogabecolor21.png" alt="" width="210"
					height="120">

				<ul class="list-unstyled components mb-5">
					<li><a href="admin/user/userlist/1"> <i
							class="fa-solid fa-house-user fa-lg"></i>會員管理
					</a></li>
					<li><a href="/restcmsindex.jsp">餐廳後台管理</a></li>
					<li><a href="#">商城後台管理</a></li>
					<li><a href="/forumBackStage">論壇後台管理</a></li>
					<li><a href="#">客服後台管理</a></li>
					<li class="nav-item">
						<div style="margin-top: 40px">
							<a class="btn btn-danger btn-sg" href="/user/logout">登出</a>
						</div>
					</li>
				</ul>
				<div class="footer"></div>
			</div>
		</nav>
		<!-- Page Content  -->
		<div id="content" class="p-3 p-md-4">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<i class="fa fa-bars"></i> <span class="sr-only">Toggle
							Menu</span>
					</button>
					<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
						type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="fa fa-bars"></i>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="nav navbar-nav ml-auto">
							<h2>OGABE後台管理</h2>
						</ul>
					</div>
				</div>
			</nav>
			<!-- 主要內容區域 -->
			<!-- 以下內容各自定義 -->
			<!-- 			<div class="container-fluid pt-5 px-5"> -->
			<!-- 				<h1 class="h_center">點餐系統後台</h1> -->
			<!-- 			</div> -->
			<!-- 			<hr /> -->
			<h2 class="h_center">餐廳後台管理</h2>
			<br>
		<div class="row featurette h_center">			
				<div class="card mb-3" style="max-width: 420px;">
					<div class="row g-0">
						<div class="col-md-4 h_center">
							<img class="figure-img img-fluid rounded"
								src="images\coffeesohp6.jpg">
						</div>
						<div class="col-md-8">
							<div class="card-body text-center">
								<h5 class="card-title">餐廳管理</h5>
								<a class="btn btn-secondary" href="/restcmslistall.jsp">查看</a>
							</div>
						</div>
					</div>
				</div>

<br>
		<div class="row featurette h_center">			
				<div class="card mb-3" style="max-width: 420px;">
					<div class="row g-0">
						<div class="col-md-4 h_center">
							<img class="figure-img img-fluid rounded"
								src="images\CoffeeCourse_index.jpg">
						</div>
						<div class="col-md-8">
							<div class="card-body text-center">
								<h5 class="card-title">餐點管理</h5>
								<a class="btn btn-secondary" href="/dishcmslistall.jsp">查看</a>
							</div>
						</div>
					</div>
				</div>

<br>
		<div class="row featurette h_center">			
				<div class="card mb-3" style="max-width: 420px;">
					<div class="row g-0">
						<div class="col-md-4 h_center">
							<img class="figure-img img-fluid rounded"
								src="images\coffeesohp7.jpg">
						</div>
						<div class="col-md-8">
							<div class="card-body text-center">
								<h5 class="card-title">訂單管理</h5>
								<a class="btn btn-secondary" href="/restordercmslistall.jsp">查看</a>
							</div>
						</div>
					</div>
				</div>

<br>
		<div class="row featurette h_center">			
				<div class="card mb-3" style="max-width: 420px;">
					<div class="row g-0">
						<div class="col-md-4 h_center">
							<img class="figure-img img-fluid rounded"
								src="images\coffeesohp8.jpg">
						</div>
						<div class="col-md-8">
							<div class="card-body text-center">
								<h5 class="card-title">訂單明細管理</h5>
								<a class="btn btn-secondary" href="/restorderlistcmslistall.jsp">查看</a>
							</div>
						</div>
					</div>
				</div>

			<script
				src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
				crossorigin="anonymous">
				
			</script>
			<script src="https://kit.fontawesome.com/726c532433.js"
				crossorigin="anonymous"></script>
			<script src="/sidebar-01/js/jquery.min.js"></script>
			<script src="/sidebar-01/js/popper.js"></script>
			<script src="/sidebar-01/js/bootstrap.min.js"></script>
			<script src="/sidebar-01/js/main.js"></script>
</body>


</html>