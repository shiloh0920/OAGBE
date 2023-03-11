<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
    <meta name="generator" content="Hugo 0.108.0" />
	<title>�I�\��x�޲z</title>
	<link href="dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="dist/css/carousel.css" rel="stylesheet" />
    <link href="dist/css/my.css" rel="stylesheet" />

</head>
<body>
<header>
      <nav
        class="navbar navbar-expand-md fixed-top navbar-dark bg-headercolor"
        aria-label="Fourth navbar example"
      >
        <div class="container-fluid">
          <a class="navbar-brand" href="index.html"
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
              <!-- <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="index.html"
                  >����</a
                >
              </li> -->
              <!-- <li class="nav-item">
                <a class="nav-link" href="guideline.html">�W���</a>
              </li> -->
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="index.html"
                  >�̷s����</a
                >
              </li>
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  >�ʪ��ӫ�</a
                >
                <ul class="dropdown-menu">
                  <li>
                    <a class="dropdown-item" href="breakpoint.html">�@�ب�</a>
                  </li>
                  <li><a class="dropdown-item" href="#">�@�ؾ���</a></li>
                  <li>
                    <a class="dropdown-item" href="#">�զX����</a>
                  </li>
                </ul>
              </li>

              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="breakpoint.html"
                  >�׾�</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="index_rest.html"
                  >�u�W�q�\</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="index.html"
                  >����ҵ{</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="index.html"
                  >�u�f����</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" aria-current="page" href="index.html"
                  >�p�C��</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="index.html"
                  >�n�J</a
                ><!--active�O�վ㦨���G
                -->
              </li>
              <!-- Button trigger modal -->
              <button
                type="button"
                class="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#staticBackdrop"
              >
                ���U
              </button>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <main>
      <!-- go to top -->
      <div class="to-top">
        <a href="#">TOP</a>
      </div>
      <!-- Modal���U�u������ -->
      <div
        class="modal fade"
        id="staticBackdrop"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content rounded-4 shadow">
            <div class="modal-header p-5 pb-4 border-bottom-0">
              <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
              <h1 class="fw-bold mb-0 fs-2">Sign up for free</h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body p-5 pt-0">
              <form class="">
                <div class="form-floating mb-3">
                  <input
                    type="email"
                    class="form-control rounded-3"
                    id="floatingInput"
                    placeholder="name@example.com"
                  />
                  <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating mb-3">
                  <input
                    type="password"
                    class="form-control rounded-3"
                    id="floatingPassword"
                    placeholder="Password"
                  />
                  <label for="floatingPassword">Password</label>
                </div>
                <button
                  class="w-100 mb-2 btn btn-lg rounded-3 btn-primary"
                  type="submit"
                >
                  Sign up
                </button>
                <small class="text-muted"
                  >By clicking Sign up, you agree to the terms of use.</small
                >
                <hr class="my-4" />
                <h2 class="fs-5 fw-bold mb-3">Or use a third-party</h2>
                <button
                  class="w-100 py-2 mb-2 btn btn-outline-dark rounded-3"
                  type="submit"
                >
                  <svg class="bi me-1" width="16" height="16">
                    <use xlink:href="#twitter" />
                  </svg>
                  Sign up with Twitter
                </button>
                <button
                  class="w-100 py-2 mb-2 btn btn-outline-primary rounded-3"
                  type="submit"
                >
                  <svg class="bi me-1" width="16" height="16">
                    <use xlink:href="#facebook" />
                  </svg>
                  Sign up with Facebook
                </button>
                <button
                  class="w-100 py-2 mb-2 btn btn-outline-secondary rounded-3"
                  type="submit"
                >
                  <svg class="bi me-1" width="16" height="16">
                    <use xlink:href="#github" />
                  </svg>
                  Sign up with GitHub
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
		
	<div class="container-fluid pt-5 px-5">
        <h1 class="h_center">�\�I�d��</h1>   
		<hr class="featurette-divider" /> 
	</div>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
 
  <div class="h_center">
    <FORM METHOD="post" ACTION="dish.do" >
        <div class="mb-3">
                <div class="rest_dish_select_qty">��J�\�I�s��:
                  <label for="InputDishid" class="form-label">
                  <input type="text" class="form-control" name="dishid" id="InputDishid" >
               </label>
               <input type="hidden" name="action" value="getOne_For_Display">
        		<input type="submit" class="btn btn-primary" value="�e�X">
               </div>
        </div>   
    </FORM>
  </div>

  <jsp:useBean id="dishSvc" scope="page" class="com.tibame.tga105.rest.dishmodel.DishService" />
   
  <div class="h_center">
     <FORM METHOD="post" ACTION="dish.do" >
     <div class="mb-3">
       <div class="rest_dish_select_qty">����\�I�s��:
        <label for="exampleSelect" class="form-label">
       <select class="form-select" size="1" name="dishid" id="exampleSelect">
         <c:forEach var="dishVO" items="${dishSvc.all}" > 
          <option value="${dishVO.dishid}">${dishVO.dishid}
         </c:forEach>   
       </select></label>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="btn btn-primary" value="�e�X">
       </div>
       </div>
    </FORM>
  </div>
  
  <div class="h_center">
     <FORM METHOD="post" ACTION="dish.do" >
     	<div class="mb-3">
       <div class="rest_dish_select_qty">����\�I�W��:
       <label for="exampleSelect" class="form-label">
       <select class="form-select" size="1" name="dishid" id="exampleSelect">
         <c:forEach var="dishVO" items="${dishSvc.all}" > 
          <option value="${dishVO.dishid}">${dishVO.dishname}
         </c:forEach>   
       </select></label>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="btn btn-primary" value="�e�X">
       </div>
       </div>
     </FORM>
  </div>
 
</ul>
<br><br>
<div class="h_center">
  <a class="btn btn-outline-secondary"  href='dishcmslistall.jsp'>�\�I�޲z</a>
  
</div>
<hr class="featurette-divider" />
</main>
    <!-- footer -->
    <footer
      class="container-fluid mt-5 bg-footercolor text-center text-light link- p-3"
    >
      <p>
        <a href="#" class="text-info">����ڭ�</a> &middot;
        <a href="#" class="text-info">Q&A</a> &middot;
        <a href="#" class="text-info">�U���v�q</a> &middot;
        <a href="#" class="text-info">�ȪA����</a>
      </p>
    </footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
