<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>后台管理系统</title>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/jquery.bootgrid.min.css">
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/jquery.bootgrid.min.js"></script>
	<script src="../js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="../css/bootstrap-datetimepicker.min.css" type="text/css"></link>
</head>

<style>
	.container{
		width:100%;
		height:12%;
		float: left;

	}
	.manage-left{
		width:20%;
		float:left;
		padding:25px 20px 0 20px;
	}
</style>
<body>
<div class="manage-header">
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar">*****</span>
						</button>
						<a class="navbar-brand" href="#">后台管理系统</a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="/user/logout">退出</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>
</div>

	<div class="manage-left">
		<ul class="nav nav-pills nav-stacked">
			<li class="active">
				<a href="/admin/userList" >
					<em>会员管理</em>
				</a>
			</li>
			<li>
				<a href="/admin/goodsList" >
					<em>商品管理</em>
				</a>
			</li>
			<li>
				<a href="/admin/catelogList" >
					<em>目录管理</em>
				</a>
			</li>
			<c:if test="${cur_admin.status == 1}">
				<li>
					<a href="/admin/adminList" >
						<em>管理员管理</em>
					</a>
				</li>
			</c:if>
		</ul>

	</div>


