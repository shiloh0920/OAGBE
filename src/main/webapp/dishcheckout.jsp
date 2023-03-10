<%@page import="com.tibame.tga105.rest.restorderlistmodel.*"%>
<%@page import="com.tibame.tga105.rest.restordermodel.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* ,com.tibame.tga105.rest.dishmodel.*"%>
<html>
<head>
<title>checkout</title>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
<link href="dist/css/carousel.css" rel="stylesheet" />
<link href="dist/css/my.css" rel="stylesheet" />

<%
   RestOrderVO restOrderVO = (RestOrderVO) request.getAttribute("restOrderVO");
%>

<%
   RestOrderListVO restOrderListVO = (RestOrderListVO) request.getAttribute("restOrderListVO");
%>

</head>
<body>
	---${param.restid}---
	<h2 class="h_center">您所選擇的餐點:</h2>
	<hr>
	<form name="ordercheckout" method="POST">
	<div class="container">
	
		<div class="row">
			<table>
				<%-- 	<c:forEach var="dishVO" items="${list}"> --%>
				<tr>
					<th class="col-2 rest_dish_select_qty">品名</th>
					<th class="col-5 rest_dish_select_qty">餐點備註</th>
					<th class="col-2 rest_dish_select_qty">價格</th>
					<th class="col-1 rest_dish_select_qty">數量</th>
					<th class="col-2 rest_dish_select_qty"></th>
				</tr>
				<%-- 	</c:forEach> --%>
			</table>
			<table>
				<%
				@SuppressWarnings("unchecked")
				Vector<DishVO> buylist = (Vector<DishVO>) session.getAttribute("shoppingcart");
				String amount = (String) request.getAttribute("amount");
				%>
				<%
				for (int i = 0; i < buylist.size(); i++) {
					DishVO order = buylist.get(i);
					String dishname = order.getDishname();
					String dishspec = order.getDishspec();
					Integer dishprice = order.getDishprice();
					Integer dishity = order.getDishity();
				%>
				<tr>
					<td class="col-2 "><%=dishname%></td>
					<td class="col-5 "><%=dishspec%></td>
					<td class="col-2 rest_dish_select_price_color">$ <%=dishprice%></td>
					<td class="col-1 "><%=dishity%></td>
					<td class="col-2 "></td>
				</tr>

				<%
				}
				%>

			</table>
			<table>
				<hr size="1" noshade="noshade" style="border: 1px #cccccc dotted;" />
				<tr>
					<td class="col-2 "></td>
					<td class="col-5 "></td>
					<td class="col-2 "></td>
					<td class="col-1 rest_dish_select_qty">總金額:</td>
					<td class="col-2 height-20 rest_dish_select_price_color">$<%=amount%></td>
				</tr>
			</table>
		</div>
			 
<%-- 			<input type="hidden" name="dishid" value="${param.dishid}"> --%>
<%-- 			<input type="hidden" name="dishname" value="${param.dishname}"> --%>
<%-- 			<input type="hidden" name="dishprice" value="${param.dishprice}"> --%>
<%-- 			<input type="hidden" name="dishqty" value="${param.dishity}"> --%>
<%-- 			<input type="hidden" name="restid" value="${param.restid}"> --%>
		<a href="dishshop2.jsp"><font class="btn btn-outline-secondary">是否繼續購物</font></a>
<!-- 		<input type="submit" class="btn btn-warning" href="restorderfront.jsp" value="結帳"> -->
		</form>
		
		
	</div>
	
<FORM METHOD="post" ACTION="restOrder.do"  name="form1">

<%-- 	<jsp:useBean id="userSvc" scope="page" class="com.user.model.UserService" /> --%>
<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">會員: -->
<!-- 		<label for="InputRestorderid" class="form-label">		 -->
<!-- 		<select class="form-select" size="1" name="userid" id="InputRestorderid"> -->
<%-- 			<c:forEach var="userVO" items="${userSvc.all}"> --%>
<%-- 				<option value="${userVO.userid}" ${(param.userid==userVO.userid)? 'selected':'' } >${userVO.username} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></label> -->
<!-- 		</div> -->
<!-- 	</div> -->
<%-- 	<jsp:useBean id="restSvc" scope="page" class="com.rest.model.RestService" /> --%>
<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">餐廳: -->
<!-- 		<label for="InputRestorderid1" class="form-label"> -->
<!-- 		<select  class="form-select" size="1" name="restid"  id="InputRestorderid1"> -->
<%-- 		<c:forEach var="restVO" items="${restSvc.all}"> --%>
<%-- 				<option value="${restVO.restid}" ${(param.restid==restVO.restid)? 'selected':'' } >${restVO.restname} --%>
<%-- 			</c:forEach>		 --%>
<!-- 		</select></label> -->
<!-- 	</div> -->
<!-- 	</div> -->
<%-- 	<jsp:useBean id="restOrderStatusSvc" scope="page" class="com.restorderstatus.model.RestOrderStatusService" /> --%>
<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">訂單狀態: -->
<!-- 		<label for="InputRestorderid1" class="form-label"> -->
<!-- 		<select  class="form-select" size="1" name="orderstatusid"  id="InputRestorderid1"> -->
<%-- 		<c:forEach var="restOrderStatusVO" items="${restOrderStatusSvc.all}"> --%>
<%-- 				<option value="${restOrderStatusVO.orderstatusid}" ${(param.orderstatusid==restOrderStatusVO.orderstatusid)? 'selected':'' } >${restOrderStatusVO.orderstatus} --%>
<%-- 			</c:forEach>		 --%>
<!-- 		</select></label> -->
<!-- 	</div> -->
<!-- 	</div> -->
	<div class="mb-3 text-center">
		<div class="rest_dish_select_qty">預定取餐時間:
		<label for="InputRestorderid3" class="form-label">
		<input type="TEXT" class="form-control" name="ordertime" size="45" id="InputRestorderid3"
		value="${param.ordertime}"/><font>${errorMsgs.ordertime}</font>
	</label>
	</div>
	</div>
	<div class="mb-3 text-center">
		<div class="rest_dish_select_qty">訂單備註:
		<label for="InputRestorderid3" class="form-label">
		<input type="TEXT" class="form-control" name="ordermemo" size="45" id="InputRestorderid3"
		value="${param.ordermemo}"/><font>${errorMsgs.ordermemo}</font>
	</label>
	</div>
</div>
<!-- 	<div class="mb-3"> -->
<!-- 		<div class="rest_dish_select_qty">訂單數量: -->
<!-- 		<label for="InputRestorderlistid3" class="form-label"> -->
<!-- 		<input type="TEXT" class="form-control" name="dishqty" size="45" id="InputRestorderlistid3" -->
<%-- 		value="${param.dishqty}"/><font>${errorMsgs.dishqty}</font> --%>
<!-- 	</label> -->
<!-- 	</div> -->
<!-- 	</div>  -->
	
 	</div>
<div class="text-center">	
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="orderid" value="${param.orderid}">
<%-- 		<input type="hidden" name="userid" value="${param.userid}"> --%>
		<input type="hidden" name="dishid" value="${param.dishid}">
		<input type="hidden" name="dishprice" value="${param.dishprice}">
		<input type="hidden" name="dishqty" value="${param.dishity}">
		<input type="hidden" name="restid" value="${param.restid}">
		<input type="hidden" name="amount" value="<%=amount%>">
		<input type="hidden" name="restOrderStatusid" value="${restOrderStatusVO.orderstatusid}">
		<input type="submit" class="btn btn-warning" value="結帳">
		</div></FORM>	
 

</body>


<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh'); 
        $('#InputRestorderid3').datetimepicker({
           theme: '',          
           timepicker: true,  
           step: 1,            
	       format: 'Y-m-d H:i:s',
	       value: new Date(),
        });
</script>

</html>