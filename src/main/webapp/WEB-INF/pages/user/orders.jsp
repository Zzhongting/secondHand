<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="../css/font-awesome.min.css" />
    <link rel="stylesheet" href="../css/emoji.css" />
    <link rel="stylesheet" href="../css/userhome.css" />
    <link rel="stylesheet" href="../css/user.css" />
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>

</head>
<body>
<div class="pre-2" id="big_img">
    <img src="http://findfun.oss-cn-shanghai.aliyuncs.com/images/head_loading.gif" class="jcrop-preview jcrop_preview_s">
</div>
<div id="cover" style="min-height: 639px;">
    <div id="user_area">
        <div id="home_header">
            <a href="/goods/homeGoods">
                <h1 class="logo"></h1>
            </a>
            <a href="/user/home">
                <div class="home"></div>
            </a>
        </div>
        <!--
            描述：左侧个人中心栏
        -->
        <c:import url="../pagefiles/person_left.jsp"></c:import>
        <!--
            描述：右侧内容区域
        -->
        <div class="panel-body" style="padding-top:150px;padding-left:250px;">
            <div class="row">
                <div id="div1" class="col-md-12">
                    <c:if test="${empty orderList}">
                        <div class="no_share">
                            <span>空空如也，去逛逛吧！</span>
                        </div>
                    </c:if>
                    <c:if test="${!empty orderList}">
                        <table  class="table">
                            <thead>
                                <tr>
                                    <td>序号</td>
                                    <td>订单编号</td>
                                    <td>商品名称</td>
                                    <td>商品价格</td>
                                    <td>下单日期</td>
                                    <td>操作</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% int i = 1; %>
                                <c:forEach var="items" items="${orderList}">
                                    <tr>
                                        <td><%= i++ %></td>
                                        <td>${items.id}</td>
                                        <td>${items.goodsName}</td>
                                        <td>${items.cost}</td>
                                        <td>${items.orderdate}</td>
                                        <td>
                                            <a href="<%=basePath%>goods/goodsId/${items.goodsId}">查看商品信息</a>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>