<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.108.0" />
<title>Header</title>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="dist/css/carousel.css" rel="stylesheet" />
<link href="dist/css/my.css" rel="stylesheet" />

</head>

<body>
	<header>

		<nav
			class="navbar navbar-expand-md fixed-top navbar-dark bg-headercolor"
			aria-label="Fourth navbar example">
			<div class="container-fluid">
				<a class="navbar-brand" href="index.html"><img
					src="images\ogabecolor.png" alt="Ogabe Logo" /></a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarsExample04"
					aria-controls="navbarsExample04" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse flex-grow-0"
					id="navbarsExample04">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">

						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/userpage">會員頁面</a></li>

						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="index.html">最新消息</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							data-bs-toggle="dropdown" aria-expanded="false">購物商城</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="breakpoint.html">咖啡豆</a>
								</li>
								<li><a class="dropdown-item" href="#">咖啡器具</a></li>
								<li><a class="dropdown-item" href="#">組合活動</a></li>
							</ul></li>

						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/welcome">論壇</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="http://localhost:8080/restfrontselect.jsp">線上訂餐</a></li>

							<c:choose>
								<c:when test="${not empty uservo}">
<!-- 									<form action="/logout" method="post"> -->
										<li class="nav-item">
										<a class="nav-link active btn btn-danger" data-bs-toggle="modal"
											data-bs-target="#staticBackdrop" href="/user/logout">登出</a>
										</li>
<!-- 									</form> -->
								</c:when>
								<c:otherwise>
								 		<li class="nav-item">
										<a class="nav-link active" aria-current="page" href="/login">登入</a>
										</li>
									</c:otherwise>
							</c:choose>

						<li class="nav-item"><a
							class="nav-link active btn btn-primary" data-bs-toggle="modal"
							data-bs-target="#staticBackdrop"
							href="http://localhost:8080/register">註冊</a> <!--active是調整成高亮--></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>