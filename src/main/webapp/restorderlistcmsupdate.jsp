<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.rest.restorderlistmodel.*"%>

<%
RestOrderListVO restOrderListVO = (RestOrderListVO) request.getAttribute("restOrderListVO");
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
		<h2 class="h_center">訂單明細管理</h2>

		<h3 class="h_center">修改訂單明細:</h3>
		<div class="h_center">


			<FORM METHOD="post" ACTION="restOrderList.do" name="form1">
				<div class="mb-3">
					<div class="rest_dish_select_qty">
						訂單明細編號:<font color=red><b> * </b></font>${param.orderlistid}</div>

				</div>

				<jsp:useBean id="restOrderSvc" scope="page"
					class="com.tibame.tga105.rest.restordermodel.RestOrderService" />
				<div class="mb-3">
					<div class="rest_dish_select_qty">
						訂單編號: <label for="InputRestorderid" class="form-label"> <select
							class="form-select" size="1" name="orderid" id="InputRestorderid">
								<c:forEach var="restOrderVO" items="${restOrderSvc.all}">
									<option value="${restOrderVO.orderid}"
										${(param.orderid==restOrderVO.orderid)? 'selected':'' }>${restOrderVO.orderid}
								</c:forEach>
						</select></label>
					</div>
				</div>
				<jsp:useBean id="dishSvc" scope="page"
					class="com.tibame.tga105.rest.dishmodel.DishService" />
				<div class="mb-3">
					<div class="rest_dish_select_qty">
						餐點: <label for="InputRestorderid1" class="form-label"> <select
							class="form-select" size="1" name="dishid" id="InputRestorderid1">
								<c:forEach var="dishVO" items="${dishSvc.all}">
									<option value="${dishVO.dishid}"
										${(param.dishid==dishVO.dishid)? 'selected':'' }>${dishVO.dishname}
								</c:forEach>
						</select></label>
					</div>
				</div>
				<div class="mb-3">
					<div class="rest_dish_select_qty">
						餐點價格: <label for="InputRestorderid2" class="form-label"> <input
							type="TEXT" class="form-control" name="dishprice" size="45"
							id="InputRestorderid2" value="${param.dishprice}" /><font>${errorMsgs.dishprice}</font>
						</label>
					</div>
				</div>
				<div class="mb-3">
					<div class="rest_dish_select_qty">
						餐點數量: <label for="InputRestorderid3" class="form-label"> <input
							type="TEXT" class="form-control" name="dishqty" size="45"
							id="InputRestorderid3" value="${param.dishqty}" /><font>${errorMsgs.dishqty}</font>
						</label>
					</div>
				</div>



				<br>
				<div class="h_center">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="orderlistid"
						value="${param.orderlistid}"> <input
						type="submit" class="btn btn-warning" value="送出">
				</div>			
			</FORM>
		</div>
		
		<br>
		<div class="h_center">
			<!--         <a class="btn btn-outline-secondary"  href="restcmsadd.jsp">新增</a> -->
			<a class="btn btn-outline-secondary"
				href="restorderlistcmslistall.jsp">訂單明細管理</a>
		</div>

		<hr class="featurette-divider" />
	</main>
	<!-- footer -->
	
	<%@ include file="cmsfooter.jsp" %>
	
	<script src="dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
