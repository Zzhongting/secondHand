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
        <div id="user_content">
            <div class="share">
                <!--
                    描述：订单展示展示
                -->
                <div class="share_content">
                    <c:if test="${empty orderList}">
                        <div class="no_share">
                            <span>空空如也，去逛逛吧！</span>
                        </div>
                    </c:if>
                    <c:if test="${!empty orderList}">
                        <table  cellpadding=3 cellspacing=1 align=center style="font-size: 15px;">
                            <tr>
                                <td valign=middle align=center height=35
                                    width="150" ><b>序号</b></td>
                                <td valign=middle align=center height=35
                                    width="250"><b>订单编号</b></td>
                                <td valign=middle align=center height=35
                                    width="250"><b>商品名称</b></td>
                                <td valign=middle align=center height=25
                                    width="150"><b>商品价格</b></td>
                                <td valign=middle align=center height=35
                                    width="350"><b>下单日期</b></td>
                                <td valign=middle align=center height=35
                                     width="350"><b>操作</b></td>
                            </tr>
                            <% int i = 1; %>
                            <c:forEach var="items" items="${orderList}">
                                <tr height=25 align=center>
                                    <td valign=middle width="">&nbsp;&nbsp;<%= i++ %></td>
                                    <td valign=middle width="">&nbsp;&nbsp;${items.id}</td>
                                    <td valign=middle width="">&nbsp;&nbsp;${items.goodsName}</td>
                                    <td valign=middle width="">&nbsp;&nbsp;${items.cost}</td>
                                    <td valign=middle width="">&nbsp;&nbsp;${items.orderdate}</td>
                                    <td valign=middle width="">&nbsp;&nbsp;
                                        <a href="<%=basePath%>goods/goodsId/${items.goodsId}">查看商品信息</a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </table>

                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>