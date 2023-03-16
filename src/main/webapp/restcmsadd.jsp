<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.rest.restmodel.*"%>

<%
   RestVO restVO = (RestVO) request.getAttribute("restVO");
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
      <h2 class="h_center">餐廳管理</h2>
<br>
<div class="text-center">
<h3>新增餐廳:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="rest.do" enctype="multipart/form-data" name="form1">

	<div class="mb-3">
		<div class="rest_dish_select_qty">餐廳名稱:
		<label for="InputRestid" class="form-label">
		<input type="TEXT" class="form-control" name="restname" size="45" id="InputRestid"
			 value="<%= (restVO==null)? "" : restVO.getRestname()%>" />
		</label>
	</div>
	</div>
	<div class="mb-3">
		<div class="rest_dish_select_qty">餐廳地址:
		<label for="InputRestid2" class="form-label">
		<input type="TEXT" class="form-control" name="restaddress" size="45" id="InputRestid2"
			 value="<%= (restVO==null)? "" : restVO.getRestaddress()%>" />
		</label>
	</div>
 	</div>
 	<div class="mb-3">
		<div class="rest_dish_select_qty">餐廳圖片:
		<label for="InputRestid3" class="form-label">
		<input type="file" class="form-control" name="restimg" size="45" id="InputRestid3"
			 value="<%= (restVO==null)? "" : restVO.getRestimg()%>" />
		</label>
		
	</div>
</div>	
 	
		<input type="hidden" name="action" value="insert">
		<input type="submit" class="btn btn-primary" value="送出"></FORM>
</div><br>
<div class="h_center">
  <a class="btn btn-outline-secondary"  href='restcmslistall.jsp'>餐廳名單管理</a>
  
</div>	     
    </main>
    <!-- footer -->
    
   <%@ include file="cmsfooter.jsp" %>
   
    <script src="dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
