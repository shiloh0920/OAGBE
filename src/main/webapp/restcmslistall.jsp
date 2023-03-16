<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.rest.restmodel.*"%>


<%
RestService restSvc = new RestService();
List<RestVO> list = restSvc.getAll();
pageContext.setAttribute("list",list);
%>

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
<!--       <div class="container-fluid pt-5 px-5"> -->
<!--         <h1 class="h_center">點餐系統後台</h1> -->
<!--       </div> -->
<!--       <hr /> -->
<br>
      <h2 class="h_center">餐廳管理</h2>

      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">餐廳編號</th>
            <th scope="col">餐廳名稱</th>
            <th scope="col">餐廳地址</th>
            <th scope="col">餐廳圖片</th>
     		<th scope="col">修改</th>
          	<th scope="col">刪除</th>
          </tr>
        </thead >
        <tbody>
        <c:forEach var="restVO" items="${list}" >
          <tr>
            <td>${restVO.restid}</td>
            <td>${restVO.restname}</td>
            <td>${restVO.restaddress}</td>
            <td><img class="figure-img img-fluid rounded" src="RestServlet?restid=${restVO.restid}"></td>
            <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rest.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-warning" value="修改">
			     <input type="hidden" name="restid"  value="${restVO.restid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rest.do" style="margin-bottom: 0px;">
			     <input type="submit" class="btn btn-danger" value="刪除">
			     <input type="hidden" name="restid"  value="${restVO.restid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
       </tr>
         </c:forEach>
        </tbody>
      </table>
      <div>
        <a class="btn btn-outline-secondary"  href="restcmsadd.jsp">新增</a>
		<a class="btn btn-outline-secondary"  href="restcmsselect.jsp">查詢</a>
<!-- 		<a class="btn btn-secondary"  href="dishcmslistall.jsp">餐點管理</a> -->
<!--       	<a class="btn btn-secondary"  href="restordercmslistall.jsp">訂單管理</a> -->
<!--       	<a class="btn btn-secondary"  href="restorderlistcmslistall.jsp">訂單明細管理</a> -->
      	<a class="btn btn-info"  href="restfrontselect.jsp">餐廳前台</a>
      </div>
<!--       <hr class="featurette-divider" /> -->
     </main>
    <!-- footer -->
    <%@ include file="cmsfooter.jsp" %>
    <script src="dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
