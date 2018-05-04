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

</head>
<body>
<div class="pre-2" id="big_img">
    <img src="http://findfun.oss-cn-shanghai.aliyuncs.com/images/head_loading.gif" class="jcrop-preview jcrop_preview_s">
</div>
<div id="cover" >
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
        <div id="user_content">
            <div class="share">
                <!--
                    描述：闲置商品展示
                -->
                <div class="share_content">
                    <c:if test="${empty goodsAndImage}">
                        <div class="no_share">
                            <span>没有任何内容，去逛逛其它的吧！</span>
                        </div>
                    </c:if>
                    <c:if test="${!empty goodsAndImage}">
                        <c:forEach var="items" items="${goodsAndImage}">
                            <div class="story">
                                <a href="<%=basePath%>goods/goodsId/${items.goods.id}" class="head_img">
                                    <img src="../upload/${items.images[0].imgUrl}">
                                </a>
                                <span class="name">${items.goods.name}</span>
                                <span class="text" style="overflow: hidden; outline: none;">${items.goods.describle}</span>
                                <div class="box">
                                    <div class="box_content">
                                        <div class="left_shadow"></div>
                                        <div class="left" index="1" style="display: none;"><</div>
                                        <div class="right_shadow"></div>
                                        <div class="left" index="3" style="display: none;">></div>

                                        <img src="../upload/${items.images[0].imgUrl}" onclick="xx()" index="2">
                                        <span class="com" style="display: none;left: 396.733px;"></span>
                                    </div>
                                    <div class="interact">
                                        <span class="fa fa-heart"><a href="<%=basePath%>goods/editGoods/${items.goods.id}">编辑</a></span>
                                        <span class="fa fa-share"><a href="<%=basePath%>goods/updatePolishTime/${items.goods.id}">擦亮</a></span>
                                        <span class="time">${items.goods.startTime}</span>
                                        <span class="fa fa-trash"><a href="<%=basePath%>goods/deleteGoods/${items.goods.id}">删除</a></span>
                                    </div>
                                    <div class="like_detail">

                                        <div class="like_content">
                                            <span>下架时间：${items.goods.endTime}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>