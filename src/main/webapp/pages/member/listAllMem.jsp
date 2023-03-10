<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tibame.tga105.mem.model.*"%>
<%@ page import="com.tibame.tga105.mem.model.MemVO"%>
<%@ page import="com.tibame.tga105.mem.model.MemService"%>


<%
MemService memSvc = new MemService();
List<MemVO> list = memSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
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
<!-- DataTables -->
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/datatables-responsive/css/responsive.bootstrap4.min.css" />
<link rel="stylesheet"
	href="../../AdminLTE-3.2.0/plugins/datatables-buttons/css/buttons.bootstrap4.min.css" />
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

<style>
input[type="checkbox"] {
	height: 0;
	width: 0;
	visibility: hidden;
}

label {
	cursor: pointer;
	width: 40px;
	height: 20px;
	background: rgb(232, 43, 43);
	display: block;
	border-radius: 100px;
	position: relative;
	/* margin-top: -20px; */
}

label:after {
	content: "";
	position: absolute;
	top: 1px;
	left: 1px;
	width: 18px;
	height: 18px;
	background: #fff;
	border-radius: 90px;
	transition: 0.3s;
}

/* .switch-txt::before,
      .switch-txt::after {
        display: block;
        color: #fff;
        font-weight: bold;
        box-sizing: border-box;
      }
      .switch-txt::before {
        content: attr(turnOn);
        color: #fff;
      }
      .switch-txt::after {
        content: attr(turnOff);
        color: #ccc;
      } */
input:checked+label {
	background: #60f760;
}

label:active:after {
	width: 40px;
}

input:checked+label:after {
	left: calc(100% - 5px);
	transform: translateX(-100%);
}

.apply_btn {
	width: 55px;
	height: 30px;
	border-radius: 8px;
	border: none;
	color: white;
	background-color: #007bff;
	font-size: 14px;
}

.search {
	border-radius: 8px;
	box-sizing: border-box;
	background-color: white;
	max-width: 200px;
	margin: 5px auto 0 auto;
	height: auto;
	position: relative;
	width: 80%;
	float: left;
}

.search-bar {
	border-radius: 8px;
	width: 100%;
	height: 32px;
	font-size: 16px;
	border: 1px solid #007bff;
	background-color: white;
}

.search-btn {
	border-top-right-radius: 8px;
	border-bottom-right-radius: 8px;
	width: 36px;
	height: 32px;
	background-color: #007bff;
	color: white;
	outline: none;
	border: 1px solid #007bff;
	cursor: pointer;
	position: absolute;
	top: 0;
	right: 0;
}
</style>
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
					href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="./24admin.index.html" class="nav-link">首頁</a></li>
				<!-- <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li> -->
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
				<!-- Navbar Search -->
				<li class="nav-item"><a class="nav-link"
					data-widget="navbar-search" href="#" role="button"> <i
						class="fas fa-search"></i>
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
				style="opacity: 0.8" /> <span class="brand-text font-weight-light"
				style="padding-left: 20px">MoLife<i class="fa-solid fa-paw"
					style="padding-left: 20px"></i></span>
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
								<i class="fa-solid fa-paw" style="padding-right: 20px"></i> 會員管理
							</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="./24admin.index.html">首頁</a>
								</li>
								<li class="breadcrumb-item active">用戶管理</li>
								<li class="breadcrumb-item active">會員管理</li>
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
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title" style="margin-top: 5px">會員列表</h3>
									<a href="./24admin.add_employee.html"><button type="button"
											class="apply_btn"
											style="width: 80px; margin-top: 5px; float: right">
											新增員工</button></a>

									<div class="search" style="float: right; margin-right: 10px">
										<input class="search-bar" type="text" name="search"
											id="search" placeholder="Search" />
										<button class="search-btn">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</div>

								<!-- /.card-header -->
								<div class="card-body">
									<table id="example2" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th>會員編號</th>
												<th>會員姓式</th>
												<th>會員名稱</th>
												<th>會員暱稱</th>
												<th>會員信箱</th>
												<th>會員密碼</th>
												<th>會員手機</th>
												<th>會員地址</th>
												<th>會員頭像</th>
												<th>會員狀態</th>
<!-- 												<th>會員狀態</th> -->
<!-- 												<th>修改</th> -->
											</tr>
										</thead>
										<tbody>
<!-- 											<tr> -->
<!-- 												<td>Trident</td> -->
<!-- 												<td>Internet Explorer 4.0</td> -->
<!-- 												<td>Win 95+</td> -->
<!-- 												<td>4</td> -->
<!-- 												<td> -->
<!-- 													<div class="form-check form-switch"> -->
<!-- 														<input class="form-check-input" type="checkbox" -->
<!-- 															id="flexSwitchCheckChecked" checked /> <label -->
<!-- 															class="form-check-label" for="flexSwitchCheckChecked"><p -->
<!-- 																style="transform: translateX(45px)">啟用</p></label> -->
<!-- 													</div> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<div> -->
<!-- 														<a href="#"> -->
<!-- 															<button type="button" class="apply_btn">編輯</button> -->
<!-- 														</a> -->
<!-- 													</div> -->
<!-- 												</td> -->
<!-- 											</tr> -->


											<%@ include file="page1.file"%>
											<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
												end="<%=pageIndex+rowsPerPage-1%>">
												<tr>
													<td>${memVO.memId}</td>
													<td>${memVO.memLname}</td>
													<td>${memVO.memFname}</td>
													<td>${memVO.memNickname}</td>
													<td>${memVO.memEmail}</td>
													<td>${memVO.memPsd}</td>
													<td>${memVO.memPhone}</td>
													<td>${memVO.memAddress}</td>
													<td><img src="/molife/MemPicView?memId=${memVO.memId}"
														width="200" height="200" id="mempic"></td>
													<td>${memVO.memStatus}</td>

<!-- 													<td> -->
<!-- 														<div class="form-check form-switch"> -->
<!-- 															<input class="form-check-input" type="checkbox" -->
<!-- 																id="flexSwitchCheckChecked1" checked /> <label -->
<!-- 																class="form-check-label" for="flexSwitchCheckChecked1"><p -->
<!-- 																	style="transform: translateX(45px)">啟用</p></label> -->
<!-- 														</div> -->
<!-- 													</td> -->
													<td>
														<FORM METHOD="post"
															ACTION="<%=request.getContextPath()%>/memberController"
															style="margin-bottom: 0px;" enctype="multipart/form-data">
															<input class="btn btn-primary login_button" type="submit" value="修改"> <input
																type="hidden" name="memId" value="${memVO.memId}">
															<input type="hidden" name="action"
																value="getOne_For_Update_By_Admin">
														</FORM>
													</td>
												</tr>
										</tbody>
										<!-- <tfoot>
                        <tr>
                          <th>Rendering engine</th>
                          <th>Browser</th>
                          <th>Platform(s)</th>
                          <th>Engine version</th>
                          <th>CSS grade</th>
                        </tr>
                      </tfoot> -->
										</c:forEach>
									</table>
									<%@ include file="page2.file"%>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
			<strong>MoLife <i class="fa-solid fa-paw"></i> &copy; 2022 <a
				href="./24front_page.html">TGA105-第四組</a>.
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
	<!-- Bootstrap 4 -->
	<script
		src="../../AdminLTE-3.2.0/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- DataTables  & Plugins -->
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="../../AdminLTE-3.2.0/plugins/jszip/jszip.min.js"></script>
	<script src="../../AdminLTE-3.2.0/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="../../AdminLTE-3.2.0/plugins/pdfmake/vfs_fonts.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="../../AdminLTE-3.2.0/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script src="../../AdminLTE-3.2.0/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<!-- <script src="../../AdminLTE-3.2.0/dist/js/demo.js"></script> -->
	<!-- Page specific script -->
	<script>
		$(function() {
			$("#example1").DataTable({
				responsive : true,
				lengthChange : false,
				autoWidth : false,
				buttons : [ "copy", "csv", "excel", "pdf", "print", "colvis" ],
			}).buttons().container().appendTo(
					"#example1_wrapper .col-md-6:eq(0)");
			$("#example2").DataTable({
				paging : true,
				lengthChange : false,
				searching : false,
				ordering : true,
				info : true,
				autoWidth : false,
				responsive : true,
			});
		});
	</script>
</body>
</html>
