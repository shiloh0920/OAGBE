<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.rest.restordermodel.*"%>

<%
RestOrderVO restOrderVO = (RestOrderVO) request.getAttribute("restOrderVO");
%>    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta
      name="author"
      content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
    />
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
      <h2 class="h_center">訂單管理</h2>

<h3 class="h_center">修改訂單:</h3>      
<div class="h_center">      
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="restOrder.do" name="form1">
	<div class="mb-3">
	<div class="rest_dish_select_qty">訂單編號:<font color=red><b> * </b></font>${param.orderid}</div>
	<div class="rest_dish_select_qty">會員編號:<font color=red><b> * </b></font>${param.userid}</div>	
	</div>
	
<%-- <jsp:useBean id="userSvc" scope="page" class="com.tibame.tga105.user.service.UserService" /> --%>
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


	<jsp:useBean id="restSvc" scope="page" class="com.tibame.tga105.rest.restmodel.RestService" />
	<div class="mb-3">
		<div class="rest_dish_select_qty">餐廳:
		<label for="InputRestorderid1" class="form-label">
		<select  class="form-select" size="1" name="restid"  id="InputRestorderid1">
		<c:forEach var="restVO" items="${restSvc.all}">
				<option value="${restVO.restid}" ${(param.restid==restVO.restid)? 'selected':'' } >${restVO.restname}
			</c:forEach>		
		</select></label>
	</div>
	</div>
	<jsp:useBean id="restOrderStatusSvc" scope="page" class="com.tibame.tga105.rest.restorderstatusmodel.RestOrderStatusService" />
	<div class="mb-3">
		<div class="rest_dish_select_qty">訂單狀態:
		<label for="InputRestorderid1" class="form-label">
		<select  class="form-select" size="1" name="orderstatusid"  id="InputRestorderid1">
		<c:forEach var="restOrderStatusVO" items="${restOrderStatusSvc.all}">
				<option value="${restOrderStatusVO.orderstatusid}" ${(param.orderstatusid==restOrderStatusVO.orderstatusid)? 'selected':'' } >${restOrderStatusVO.orderstatus}
			</c:forEach>		
		</select></label>
	</div>
	</div>
	<div class="mb-3">
		<div class="rest_dish_select_qty">預定時間:
		<label for="InputRestorderid3" class="form-label">
		<input type="TEXT" class="form-control" name="ordertime" size="45" id="InputRestorderid3"
		value="${param.ordertime}"/><font>${errorMsgs.ordertime}</font>
	</label>
	</div>
	</div>
	<div class="mb-3">
		<div class="rest_dish_select_qty">訂單備註:
		<label for="InputRestorderid3" class="form-label">
		<input type="TEXT" class="form-control" name="ordermemo" size="45" id="InputRestorderid3"
		value="${param.ordermemo}"/><font>${errorMsgs.ordermemo}</font>
	</label>
	</div>
	</div>
	
	
	
	<br>
	<div class="h_center">
	<input type="hidden" name="action" value="update">
	<input type="hidden" name="orderid" value="${param.orderid}">
	<input type="submit" class="btn btn-warning" value="送出"></FORM>
	</div>
</div>
<br>
      <div class="h_center">
<!--         <a class="btn btn-outline-secondary"  href="restcmsadd.jsp">新增</a> -->
		<a class="btn btn-outline-secondary" href="restordercmslistall.jsp">訂單管理</a>
      </div>
      
      <hr class="featurette-divider" />
     </main>
    <!-- footer -->
    
   <%@ include file="cmsfooter.jsp" %>
   
    <script src="dist/js/bootstrap.bundle.min.js"></script>
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
