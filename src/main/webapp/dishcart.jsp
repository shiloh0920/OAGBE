<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* ,com.tibame.tga105.rest.dishmodel.DishVO"%>

<html>
<head>
<title>dishcart</title>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="dist/css/carousel.css" rel="stylesheet" />
<link href="dist/css/my.css" rel="stylesheet" />



</head>
<body>
<%-- ---${param.restid}--- --%>
	<br>
	<%
	@SuppressWarnings("unchecked")
	Vector<DishVO> buylist = (Vector<DishVO>) session.getAttribute("shoppingcart");
	%>
	<%
	if (buylist != null && (buylist.size() > 0)) {
	%>
	<hr class="featurette-divider" />
	<h2 class="h_center">您的購物車 :</h2>
	<div class="container">
		<%-- 	<c:forEach var="dishVO" items="${list}"> --%>
		<!-- 				<table id="table-1"> -->
		<!-- 					<tr> -->
		<%-- 						<th width="200">${dishVO.dishname}</th> --%>
		<%-- 						<th width="100">${dishVO.dishdescription}</th> --%>
		<%-- 						<th width="100">${dishVO.dishprice}</th> --%>
		<!-- 						<th width="120">餐點數量</th> -->
		<!-- 					</tr> -->
		<!-- 				</table> -->
		<%-- 	</c:forEach> --%>
		
		<div class="row">
			<table><tr>
				<th class="col-2 rest_dish_select_qty">品名</th>
				<th class="col-6 rest_dish_select_qty">餐點備註</th>
				<th class="col-2 rest_dish_select_qty">價格</th>
				<th class="col-1 rest_dish_select_qty">數量</th>
				<th class="col-1 rest_dish_select_qty"></th>
			</tr></table><table>
<!-- 			<hr size="1" noshade="noshade" style="border:1px #cccccc dotted;"/> -->
			<%
			for (int index = 0; index < buylist.size(); index++) {
				DishVO order = buylist.get(index);
			%>
			<tr>
			<td class="col-2 "><%=order.getDishname()%></td>
			<td class="col-6 "><%=order.getDishspec()%></td>
			<td class="col-2 ">$ <%=order.getDishprice()%></td>
			<td class="col-1 "><%=order.getDishity()%></td>
			<td class="col-1 ">
				<form name="deleteForm" action="Shopping" method="POST">
					<input type="hidden" name="action" value="DELETE"> 
					<input type="hidden" name="del" value="<%=index%>"> 
					<input type="hidden" name="restid" value="${param.restid}"> 
					<input type="submit" value="移除" class="btn btn-danger">
				</form>
			</td>

			</tr>
			<%
			}
			%>
			</table>
		</div>
		<p>
		<form name="checkoutForm" action="Shopping" method="POST">
			<input type="hidden" name="action" value="CHECKOUT"> 
			<input type="hidden" name="dishid" value="${param.dishid}">
			<input type="hidden" name="dishprice" value="${param.dishprice}">
			<input type="hidden" name="dishqty" value="${param.dishity}">
			<input type="hidden" name="restid" value="${param.restid}">
			<input type="submit" value="下訂" class="btn btn-warning">
		</form>
		<%
		}
		%>
		
		
<!-- 	<FORM METHOD="post" ACTION="restOrder.do"  name="form1"> -->

<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">預定時間: -->
<!-- 		<label for="InputRestorderid3" class="form-label"> -->
<!-- 		<input type="TEXT" class="form-control" name="ordertime" size="45" id="InputRestorderid3" -->
<%-- 		value="${param.ordertime}"/><font>${errorMsgs.ordertime}</font> --%>
<!-- 	</label> -->
<!-- 	</div> -->
<!-- 	</div> -->
<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">訂單備註: -->
<!-- 		<label for="InputRestorderid3" class="form-label"> -->
<!-- 		<input type="TEXT" class="form-control" name="ordermemo" size="45" id="InputRestorderid3" -->
<%-- 		value="${param.ordermemo}"/><font>${errorMsgs.ordermemo}</font> --%>
<!-- 	</label> -->
<!-- 	</div> -->
<!-- </div> -->

	
<!--  	</div> -->
 	
<!-- 		<input type="hidden" name="action" value="insert"> -->
<%-- 		<input type="hidden" name="dishid" value="${param.dishid}"> --%>
<%-- 		<input type="hidden" name="dishprice" value="${param.dishprice}"> --%>
<%-- 		<input type="hidden" name="dishqty" value="${param.dishity}"> --%>
<%-- 		<input type="hidden" name="restid" value="${param.restid}"> --%>
<!-- 		<input type="submit" class="btn btn-primary" value="送出"></FORM>	 -->
	
	
</body>
</html>