<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
    <meta name="generator" content="Hugo 0.108.0" />
	<title>點餐後台管理</title>
	<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="dist/css/carousel.css" rel="stylesheet" />
    <link href="dist/css/my.css" rel="stylesheet" />

</head>
<body>

 <%@ include file="cmsheader.jsp" %>
 
    <main>
      <!-- go to top -->
      <div class="to-top">
        <a href="#">TOP</a>
      </div>
      <!-- Modal註冊彈跳視窗 -->
     
		
	<div class="container-fluid pt-5 px-5">
        <h1 class="h_center">餐廳查詢</h1>   
		<hr class="featurette-divider" /> 
	</div>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
 
  <div class="h_center">
    <FORM METHOD="post" ACTION="rest.do" >
        <div class="mb-3">
                <div class="rest_dish_select_qty">輸入餐廳編號:
                  <label for="InputRestid" class="form-label">
                  <input type="text" class="form-control" name="restid" id="InputRestid" >
               </label>
               <input type="hidden" name="action" value="getOne_For_Display">
        		<input type="submit" class="btn btn-primary" value="送出">
               </div>
        </div>   
    </FORM>
  </div>

  <jsp:useBean id="restSvc" scope="page" class="com.tibame.tga105.rest.restmodel.RestService" />
   
  <div class="h_center">
     <FORM METHOD="post" ACTION="rest.do" >
     <div class="mb-3">
       <div class="rest_dish_select_qty">選擇餐廳編號:
        <label for="exampleSelect" class="form-label">
       <select class="form-select" size="1" name="restid" id="exampleSelect">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.restid}">${restVO.restid}
         </c:forEach>   
       </select></label>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="btn btn-primary" value="送出">
       </div>
       </div>
    </FORM>
  </div>
  
  <div class="h_center">
     <FORM METHOD="post" ACTION="rest.do" >
     	<div class="mb-3">
       <div class="rest_dish_select_qty">選擇餐廳名稱:
       <label for="exampleSelect" class="form-label">
       <select class="form-select" size="1" name="restid" id="exampleSelect">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.restid}">${restVO.restname}
         </c:forEach>   
       </select></label>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="btn btn-primary" value="送出">
       </div>
       </div>
     </FORM>
  </div>
 
</ul>
<br><br>
<div class="h_center">
  <a class="btn btn-outline-secondary"  href='restcmslistall.jsp'>餐廳名單管理</a>
  
</div>
<hr class="featurette-divider" />
</main>
    <!-- footer -->
    <%@ include file="cmsfooter.jsp" %>
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>