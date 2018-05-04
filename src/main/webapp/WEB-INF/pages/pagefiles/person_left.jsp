<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--
描述：左侧个人中心栏
-->
<div id="user_nav">
    <div class="user_info">
        <div class="head_img">
            <img src="<%=basePath%>img/photo.jpg">
        </div>
        <div class="big_headimg">
            <img src="">
        </div>
        <span class="name">${cur_user.username}</span>
        <span class="school">江西理工大学</span>
        <span class="name">闲置数量：${cur_user.goodsNum}</span>
    </div>
    <div class="home_nav">
        <ul>

            <a href="/goods/publishGoods">
                <li class="store">
                    <div></div>
                    <span>发布物品</span>
                    <strong></strong>
                </li>
            </a>
            <a href="/user/allGoods">
                <li class="second">
                    <div></div>
                    <span>我的闲置</span>
                    <strong></strong>
                </li>
            </a>
            <a href="/user/allOrders">
                <li class="notice">
                    <div></div>
                    <span>我的订单</span>
                    <strong></strong>
                </li>
            </a>
            <a href="/user/basic">
                <li class="set">
                    <div></div>
                    <span>个人设置</span>
                    <strong></strong>
                </li>
            </a>
        </ul>
    </div>
</div>
