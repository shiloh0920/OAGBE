<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

      <table class="table table-bordered">
       
	<tr>
			<th >訂單編號</th>
            <th >會員編號</th>
            <th >餐廳</th>
     		<th >訂單狀態</th>
     		<th >預定時間</th>
     		<th >訂單明細</th>
	</tr>
	<tr>
			<td>${restOrderVO.orderid}</td>
            <td>${restOrderVO.userid}</td>          
<%--             -[${restOrderVO.userVO.username}] --%>            
            <td>${restOrderVO.restid}-[${restOrderVO.restVO.restname}]</td>         
            <td>${restOrderVO.orderstatusid}-[${restOrderVO.restOrderStatusVO.orderstatus}]</td>	
            <td>${restOrderVO.ordertime}</td>
			<td>${restOrderVO.ordermemo}</td>
	
	</tr>

      </table>
      <div class="h_center">
<!--         <a class="btn btn-outline-secondary"  href="dishcmsadd.jsp">新增</a> -->
<!-- 		<a class="btn btn-outline-secondary"  href="dishcmsselect.jsp">查詢</a> -->
		<a class="btn btn-outline-secondary"  href="restordercmslistall.jsp">訂單管理</a>
      </div>
      <hr class="featurette-divider" />
     </main>
    <!-- footer -->
    
   <%@ include file="cmsfooter.jsp" %>
   
    <script src="dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
