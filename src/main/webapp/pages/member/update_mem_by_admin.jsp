<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tibame.tga105.mem.model.*"%>

<%
//MemServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
--<%=memVO == null%>--${memVO.memId}--

<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>MoLife 後台</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback" />
<!-- Font Awesome -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/fontawesome-free/css/all.min.css" />
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" />
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css" />
<!-- iCheck -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/icheck-bootstrap/icheck-bootstrap.min.css" />
<!-- JQVMap -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/jqvmap/jqvmap.min.css" />
<!-- Theme style -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/dist/css/adminlte.min.css" />
<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/overlayScrollbars/css/OverlayScrollbars.min.css" />
<!-- Daterange picker -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/daterangepicker/daterangepicker.css" />
<!-- summernote -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/summernote/summernote-bs4.min.css" />

<script src="https://kit.fontawesome.com/2672c0b72c.js"
	crossorigin="anonymous"></script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake"
				src="../../AdminLTE-3.2.0/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60" />
		</div>

		<!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu"
					href="#" role="button"><i class="fas fa-bars"></i></a>
				</li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="24admin.index.html" class="nav-link">首頁</a></li>
				<!-- <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li> -->
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Navbar Search -->
				<li class="nav-item"><a class="nav-link"
					data-widget="navbar-search" href="#" role="button">
						<i class="fas fa-search"></i>
				</a>
					<div class="navbar-search-block">
						<form class="form-inline">
							<div class="input-group input-group-sm">
								<input class="form-control form-control-navbar" type="search"
									placeholder="Search" aria-label="Search" />
								<div class="input-group-append">
									<button class="btn btn-navbar" type="submit">
										<i class="fas fa-search"></i>
									</button>
									<button class="btn btn-navbar" type="button"
										data-widget="navbar-search">
										<i class="fas fa-times"></i>
									</button>
								</div>
							</div>
						</form>
					</div></li>

				<!-- Messages Dropdown Menu -->
				<!-- <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-comments"></i>
          <span class="badge badge-danger navbar-badge">3</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
              <img src="../../AdminLTE-3.2.0/dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  Brad Diesel
                  <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">Call me whenever you can...</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div> -->
				<!-- Message End -->
				<!-- </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
              <img src="../../AdminLTE-3.2.0/dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  John Pierce
                  <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">I got your message bro</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div> -->
				<!-- Message End -->
				<!-- </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item"> -->
				<!-- Message Start -->
				<!-- <div class="media">
              <img src="../../AdminLTE-3.2.0/dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  Nora Silvester
                  <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">The subject goes here</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div> -->
				<!-- Message End -->
				<!-- </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
        </div>
      </li> -->
				<!-- Notifications Dropdown Menu -->
				<!-- <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-bell"></i>
          <span class="badge badge-warning navbar-badge">15</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <span class="dropdown-item dropdown-header">15 Notifications</span>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-envelope mr-2"></i> 4 new messages
            <span class="float-right text-muted text-sm">3 mins</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-users mr-2"></i> 8 friend requests
            <span class="float-right text-muted text-sm">12 hours</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-file mr-2"></i> 3 new reports
            <span class="float-right text-muted text-sm">2 days</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
        </div>
      </li> -->
				<li class="nav-item"><a class="nav-link"
					data-widget="fullscreen" href="#" role="button"> <i
						class="fas fa-expand-arrows-alt"></i>
				</a></li>
				<!-- <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-controlsidebar-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li> -->
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="./24admin.index.html" class="brand-link"> <img
				src="../../AdminLTE-3.2.0/dist/img/AdminLTELogo.png"
				alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
				style="opacity: 0.8" /> <span
				class="brand-text font-weight-light" style="padding-left: 20px">MoLife<i
					class="fa-solid fa-paw" style="padding-left: 20px"></i></span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="../../images/front_page/dog.jpg"
							class="img-circle elevation-2" alt="User Image" />
					</div>
					<div class="info">
						<a href="#" class="d-block">Linda Lee</a>
					</div>
				</div>

				<!-- SidebarSearch Form -->
				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search"
							placeholder="Search" aria-label="Search" />
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item menu-open"><a href="#"
							class="nav-link active"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									用戶管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="..." class="nav-link active">
										<i class="far fa-circle nav-icon"></i>
										<p>帳戶管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>會員管理</p>
								</a></li>
								<li class="nav-item"><a href="./24admin.limit.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>權限管理</p>
								</a></li>
							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-copy"></i>
								<p>
									商城後台管理 <i class="fas fa-angle-left right"></i> <span
										class="badge badge-info right">6</span>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>商品管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>訂單管理</p>
								</a></li>
								<!-- <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/boxed.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Boxed</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/fixed-sidebar.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Fixed Sidebar</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/fixed-sidebar-custom.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Fixed Sidebar <small>+ Custom Area</small></p>
                </a>
              </li>
              <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/fixed-topnav.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Fixed Navbar</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/fixed-footer.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Fixed Footer</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/layout/collapsed-sidebar.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Collapsed Sidebar</p>
                </a>
              </li> -->
							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-chart-pie"></i>
								<p>
									旅館後台管理 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>房型管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>訂單管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>評價管理</p>
								</a></li>
								<!-- <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/charts/uplot.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>uPlot</p>
                </a>
              </li> -->
							</ul></li>

						<li class="nav-item"><a href="./24admin.news.html"
							class="nav-link"> <i class="nav-icon fas fa-th"></i>
								<p>
									前台頁面管理 <span class="right badge badge-danger">New</span>
								</p>
						</a></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-tree"></i>
								<p>
									募款後台管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>提案募款紀錄</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>捐款紀錄查詢</p>
								</a></li>
								<!-- <li class="nav-item">
                    <a href="..." class="nav-link">
                      <i class="far fa-circle nav-icon"></i>
                      <p>Buttons</p>
                    </a>
                  </li> -->
								<!-- <li class="nav-item">
                <a href="..." class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Sliders</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="..." class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Modals & Alerts</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="..." class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Navbar & Tabs</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="..." class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Timeline</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="..." class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Ribbons</p>
                </a>
              </li> -->
							</ul></li>

						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-edit"></i>
								<p>
									論壇後台管理 <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="./24admin.forum.html"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>文章管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>版面管理</p>
								</a></li>
								<li class="nav-item"><a href="..." class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>公告管理</p>
								</a></li>
								<!-- <li class="nav-item">
                <a href="../../AdminLTE-3.2.0/pages/forms/validation.html" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Validation</p>
                </a>
              </li> -->
							</ul></li>

						<li class="nav-item"><a href="./24admin.chatroom.html"
							class="nav-link"> <i class="nav-icon fas fa-table"></i>
								<p>
									留言管理 <span class="right badge badge-danger">New</span>
								</p>
						</a></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">
								<i class="fa-solid fa-paw" style="padding-right: 20px"></i>
								會員資料編輯
							</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="./24admin.index.html">首頁</a>
								</li>
								<li class="breadcrumb-item active"><a
									href="./24admin.limit.html">會員資料編輯</a></li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- left column -->
						<div class="col-md-8" style="transform: translateX(200px)">
							<!-- general form elements -->
							<div class="card card-primary" style="margin-bottom:10px">
								<div class="card-header">
									<h3 class="card-title">管理員修改會員資料</h3>
								</div>
								<!-- /.card-header -->
								<!-- form start -->
								<form>
									<div class="card-body">
										<div class="form-group">
											<label for="exampleInputEmail1">會員姓氏</label> <input
												type="TEXT" class="form-control" id="memberLname"
												placeholder="MemLName"
												value="${sessionScope.memVO.getMemLname()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1">會員名稱</label> <input
												type="TEXT" class="form-control" id="memberFname"
												placeholder="MemFName"
												value="${sessionScope.memVO.getMemFname()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputEmail1">會員暱稱</label> <input
												type="TEXT" class="form-control" id="memNickname"
												placeholder="memNickname"
												value="${sessionScope.memVO.getMemNickname()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1">會員密碼</label> <input
												type="password" class="form-control" id="memPsd"
												placeholder="memPsd"
												value="${sessionScope.memVO.getMemPsd()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputPassword1">確認密碼</label> <input
												type="password" class="form-control" id="memPsd"
												placeholder="memPsd"
												value="${sessionScope.memVO.getMemPsd()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputName1">會員手機</label> <input
												type="text" class="form-control" id="memPhone"
												placeholder="memPhone" />
										</div>
										<div class="form-group">
											<label for="exampleInputName1">會員手機</label> <input
												type="text" class="form-control" id="memPhone"
												placeholder="memPhone"
												value="${sessionScope.memVO.getMemPhone()}" />
										</div>
										<div class="form-group">
											<label for="exampleInputName1">會員地址</label> <input
												type="text" class="form-control" id="memPhone"
												placeholder="memPhone"
												value="${sessionScope.memVO.getMemAddress()}" />
										</div>
										<!--                       <div class="form-group"> -->
										<!--                         <label>員工權限</label> -->
										<!--                         <select class="form-control"> -->
										<!--                           <option>商城</option> -->
										<!--                           <option>旅館</option> -->
										<!--                           <option>全部</option> -->
										<!--                           <option>option 4</option>
<!--                           <option>option 5</option> -->
										-->
										<!--                         </select> -->
										<!--                       </div> -->
										<div class="form-group">
											<label for="exampleInputStatus1">狀態</label> <input
												class="form-check-input" type="radio"
												name="flexRadioDefault100" id="flexRadioDefault200"
												style="transform: translateX(60px)" checked />
											<label class="form-check-label" for="flexRadioDefault200"
												style="transform: translateX(60px)">
												啟用 </label> <input class="form-check-input" type="radio"
												name="flexRadioDefault100" id="flexRadioDefault201"
												style="transform: translateX(120px)" />
											<label class="form-check-label" for="flexRadioDefault201"
												style="transform: translateX(120px)">
												禁用 </label>
										</div>

										<div class="form-group">
											<label for="exampleInputFile">會員頭像編輯</label>
											<div class="input-group">
												<div class="custom-file">
													<input type="file" class="memPicId" id="memPicId" />
													<label class="custom-file-label" for="exampleInputFile">選擇檔案</label>
												</div>
												<div class="input-group-append">
													<span class="input-group-text">上傳</span>
												</div>
											</div>
										</div>
										<!-- <div class="form-check">
                        <input
                          type="checkbox"
                          class="form-check-input"
                          id="exampleCheck1"
                        />
                        <label class="form-check-label" for="exampleCheck1"
                          >Check me out</label
                        >
                      </div> -->
									</div>
									<jsp:useBean id="memtSvc" scope="page"
										class="com.tibame.tga105.mem.model.MemService" />
									<!-- /.card-body -->

									<div class="card-footer">
										<a href="#"> <input type="hidden" name="action"
											value="updateByAdmin"> <input type="hidden" name="memId"
											value="${sessionScope.memVO.getMemId()}">
<!-- 											<button type="submit" class="btn btn-primary">新增並儲存 -->
<!-- 											</button>  -->
											<input class="btn btn-primary update_button" type="submit"
											value="確認修改">
										</a>
									</div>
								</form>
							</div>
							<!-- /.card -->
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<strong>MoLife <i class="fa-solid fa-paw"></i>
				&copy; 2022 <a href="./24front_page.html">TGA105-第四組</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.2.0
			</div>
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="../../AdminLTE-3.2.0/plugins/jquery/jquery.min.js"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="../../AdminLTE-3.2.0/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge("uibutton", $.ui.button);
	</script>
	<!-- Bootstrap 4 -->
	<script
		src="../../AdminLTE-3.2.0/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- ChartJS -->
	<script src="../../AdminLTE-3.2.0/plugins/chart.js/Chart.min.js"></script>
	<!-- Sparkline -->
	<script src="../../AdminLTE-3.2.0/plugins/sparklines/sparkline.js"></script>
	<!-- JQVMap -->
	<script src="../../AdminLTE-3.2.0/plugins/jqvmap/jquery.vmap.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<!-- jQuery Knob Chart -->
	<script
		src="../../AdminLTE-3.2.0/plugins/jquery-knob/jquery.knob.min.js"></script>
	<!-- daterangepicker -->
	<script src="../../AdminLTE-3.2.0/plugins/moment/moment.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="../../AdminLTE-3.2.0/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Summernote -->
	<script
		src="../../AdminLTE-3.2.0/plugins/summernote/summernote-bs4.min.js"></script>
	<!-- overlayScrollbars -->
	<script
		src="../../AdminLTE-3.2.0/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../AdminLTE-3.2.0/dist/js/adminlte.js"></script>
	<!-- AdminLTE for demo purposes -->
	<!-- <script src="../../AdminLTE-3.2.0/dist/js/demo.js"></script> -->
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- <script src="../../AdminLTE-3.2.0/dist/js/pages/dashboard.js"></script> -->

	<!-- bs-custom-file-input -->
	<script
		src="../../AdminLTE-3.2.0/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>

	<!-- Page specific script -->
	<script>
		$(function() {
			bsCustomFileInput.init();
		});
	</script>

	<!-- Page specific script -->
	<script>
		$(function() {
			/* ChartJS
			 * -------
			 * Here we will create a few charts using ChartJS
			 */

			//--------------
			//- AREA CHART -
			//--------------
			// Get context with jQuery - using jQuery's .get() method.
			var areaChartCanvas = $("#areaChart").get(0).getContext("2d");

			var areaChartData = {
				labels : [ "January", "February", "March", "April", "May",
						"June", "July", ],
				datasets : [ {
					label : "Digital Goods",
					backgroundColor : "rgba(60,141,188,0.9)",
					borderColor : "rgba(60,141,188,0.8)",
					pointRadius : false,
					pointColor : "#3b8bba",
					pointStrokeColor : "rgba(60,141,188,1)",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(60,141,188,1)",
					data : [ 28, 48, 40, 19, 86, 27, 90 ],
				}, {
					label : "Electronics",
					backgroundColor : "rgba(210, 214, 222, 1)",
					borderColor : "rgba(210, 214, 222, 1)",
					pointRadius : false,
					pointColor : "rgba(210, 214, 222, 1)",
					pointStrokeColor : "#c1c7d1",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "rgba(220,220,220,1)",
					data : [ 65, 59, 80, 81, 56, 55, 40 ],
				}, ],
			};

			var areaChartOptions = {
				maintainAspectRatio : false,
				responsive : true,
				legend : {
					display : false,
				},
				scales : {
					xAxes : [ {
						gridLines : {
							display : false,
						},
					}, ],
					yAxes : [ {
						gridLines : {
							display : false,
						},
					}, ],
				},
			};

			// This will get the first returned node in the jQuery collection.
			new Chart(areaChartCanvas, {
				type : "line",
				data : areaChartData,
				options : areaChartOptions,
			});

			//-------------
			//- LINE CHART -
			//--------------
			var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
			var lineChartOptions = $.extend(true, {}, areaChartOptions);
			var lineChartData = $.extend(true, {}, areaChartData);
			lineChartData.datasets[0].fill = false;
			lineChartData.datasets[1].fill = false;
			lineChartOptions.datasetFill = false;

			var lineChart = new Chart(lineChartCanvas, {
				type : "line",
				data : lineChartData,
				options : lineChartOptions,
			});

			//-------------
			//- DONUT CHART -
			//-------------
			// Get context with jQuery - using jQuery's .get() method.
			var donutChartCanvas = $("#donutChart").get(0).getContext("2d");
			var donutData = {
				labels : [ "Chrome", "IE", "FireFox", "Safari", "Opera",
						"Navigator" ],
				datasets : [
						{
							data : [ 700, 500, 400, 600, 300, 100 ],
							backgroundColor : [ "#f56954", "#00a65a",
									"#f39c12", "#00c0ef", "#3c8dbc", "#d2d6de", ],
						}, ],
			};
			var donutOptions = {
				maintainAspectRatio : false,
				responsive : true,
			};
			//Create pie or douhnut chart
			// You can switch between pie and douhnut using the method below.
			new Chart(donutChartCanvas, {
				type : "doughnut",
				data : donutData,
				options : donutOptions,
			});

			//   //-------------
			//   //- PIE CHART -
			//   //-------------
			//   // Get context with jQuery - using jQuery's .get() method.
			//   var pieChartCanvas = $('#pieChart').get(0).getContext('2d')
			//   var pieData        = donutData;
			//   var pieOptions     = {
			//     maintainAspectRatio : false,
			//     responsive : true,
			//   }
			//   //Create pie or douhnut chart
			//   // You can switch between pie and douhnut using the method below.
			//   new Chart(pieChartCanvas, {
			//     type: 'pie',
			//     data: pieData,
			//     options: pieOptions
			//   })

			//-------------
			//- BAR CHART -
			//-------------
			var barChartCanvas = $("#barChart").get(0).getContext("2d");
			var barChartData = $.extend(true, {}, areaChartData);
			var temp0 = areaChartData.datasets[0];
			var temp1 = areaChartData.datasets[1];
			barChartData.datasets[0] = temp1;
			barChartData.datasets[1] = temp0;

			var barChartOptions = {
				responsive : true,
				maintainAspectRatio : false,
				datasetFill : false,
			};

			new Chart(barChartCanvas, {
				type : "bar",
				data : barChartData,
				options : barChartOptions,
			});

			//---------------------
			//- STACKED BAR CHART -
			//---------------------
			var stackedBarChartCanvas = $("#stackedBarChart").get(0)
					.getContext("2d");
			var stackedBarChartData = $.extend(true, {}, barChartData);

			var stackedBarChartOptions = {
				responsive : true,
				maintainAspectRatio : false,
				scales : {
					xAxes : [ {
						stacked : true,
					}, ],
					yAxes : [ {
						stacked : true,
					}, ],
				},
			};

			new Chart(stackedBarChartCanvas, {
				type : "bar",
				data : stackedBarChartData,
				options : stackedBarChartOptions,
			});
		});
	</script>

	<!-- Code injected by live-server -->
	<script>
		// <![CDATA[  <-- For SVG support
		if ("WebSocket" in window) {
			(function() {
				function refreshCSS() {
					var sheets = [].slice.call(document
							.getElementsByTagName("link"));
					var head = document.getElementsByTagName("head")[0];
					for (var i = 0; i < sheets.length; ++i) {
						var elem = sheets[i];
						var parent = elem.parentElement || head;
						parent.removeChild(elem);
						var rel = elem.rel;
						if ((elem.href && typeof rel != "string")
								|| rel.length == 0
								|| rel.toLowerCase() == "stylesheet") {
							var url = elem.href.replace(
									/(&|\?)_cacheOverride=\d+/, "");
							elem.href = url
									+ (url.indexOf("?") >= 0 ? "&" : "?")
									+ "_cacheOverride=" + new Date().valueOf();
						}
						parent.appendChild(elem);
					}
				}
				var protocol = window.location.protocol === "http:" ? "ws://"
						: "wss://";
				var address = protocol + window.location.host
						+ window.location.pathname + "/ws";
				var socket = new WebSocket(address);
				socket.onmessage = function(msg) {
					if (msg.data == "reload")
						window.location.reload();
					else if (msg.data == "refreshcss")
						refreshCSS();
				};
				if (sessionStorage
						&& !sessionStorage
								.getItem("IsThisFirstTime_Log_From_LiveServer")) {
					console.log("Live reload enabled.");
					sessionStorage.setItem(
							"IsThisFirstTime_Log_From_LiveServer", true);
				}
			})();
		} else {
			console
					.error("Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.");
		}
		// ]]>
	</script>
</body>
</html>
