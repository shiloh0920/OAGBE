<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Header</title>
    <link href="dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="dist/css/carousel.css" rel="stylesheet" />
    <link href="dist/css/my.css" rel="stylesheet" />
</head>

  <body>
    <header>
      
      <nav
        class="navbar navbar-expand-md fixed-top navbar-dark bg-dark"
        aria-label="Fourth navbar example"
      >
        <div class="container-fluid">
          <a class="navbar-brand" href="#"
            ><img src="images\ogabecolor.png" alt="Ogabe Logo"
          /></a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarsExample04"
            aria-controls="navbarsExample04"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div
            class="collapse navbar-collapse flex-grow-0"
            id="navbarsExample04"
          >
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
              <li class="nav-item">
                <a class="nav-link" 
                aria-current="page" 
                href="/restcmsindex.jsp"
                >餐廳後台首頁</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" 
                aria-current="page" 
                href="/restcmslistall.jsp"
                >餐廳管理</a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  aria-current="page"
                  href="/dishcmslistall.jsp"
                  >餐點管理</a
                >
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  aria-current="page"
                  href="/restordercmslistall.jsp"
                  >訂單管理</a
                >
              </li>
               <li class="nav-item">
                <a
                  class="nav-link"
                  aria-current="page"
                  href="/restorderlistcmslistall.jsp"
                  >訂單明細管理</a
                >
              </li>
<!--               <li class="nav-item"> -->
<!--                 <a -->
<!--                   class="nav-link active" -->
<!--                   aria-current="page" -->
<!--                   href="http://localhost:8080/login" -->
<!--                   >登入</a -->
<!--                 >active是調整成高亮 -->
<!--               </li> -->
              <!-- Button trigger modal -->
             
             <li class="nav-item">
<!-- 	          <div style="margin-top:40px"> -->
                <a
                  class="btn btn-danger btn-sg"
                  href="/user/logout"
                  >登出</a
                >
<!--                 </div> -->
              </li>
             
             
            </ul>
          </div>
        </div>
      </nav>
    </header>
  </body>
</html>