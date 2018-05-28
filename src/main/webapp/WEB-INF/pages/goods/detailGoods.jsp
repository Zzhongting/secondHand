<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--
描述：顶部
-->
<c:import url="../pagefiles/head.jsp"></c:import>
<script>
    function order(){
        if($("#confirm-show").css("display")=='none'){
            $("#confirm-show").css("display","block");
            $("html").css("overflow-y","hidden");
        }else{
            $("#confirm-show").css("display","none");
            $("html").css("overflow-y","scroll");
        }
    }
</script>
<style>
      #item-option{
            position: relative;
            height:30px;
            font-size: 20px;
            margin:20px 60px;

        }
</style>
<link rel="stylesheet" href="<%=basePath%>css/detail.css" />



<div ng-controller="detailBoxController" class="detail-box stark-components z-depth-1 row ng-scope">
    <div class="col s12 path">
        <a href="<%=basePath%>goods/catelog/${catelog.id}">${catelog.name}</a>
        <em>></em>
        <a>${goodsExtend.goods.name}</a>
    </div>
    <div class="col s6">
        <div class="slider" style="height: 440px;">
            <ul class="slides" style="height: 400px;">
                <img src="<%=basePath%>upload/${goodsExtend.images[0].imgUrl}" />
            </ul>
            <ul class="indicators">
                <li class="indicator-item"></li>
                <li class="indicator-item"></li>
                <li class="indicator-item"></li>
                <li class="indicator-item"></li>
            </ul>
        </div>
    </div>
    <div class="col s6">
        <h1 class="item-name">${goodsExtend.goods.name}</h1>
        <h2 class="item-price">￥${goodsExtend.goods.price}</h2>
        <h3 style="font-size:12px;margin-left: 14px;text-decoration:line-through">原价：￥${goodsExtend.goods.realPrice}</h3>

        <div class="publisher-info-title">
            <em>卖家信息</em><hr>
        </div>
        <c:if test="${empty cur_user}">
            <div class="item-contact">
                <p class="not-login">
                    <a onclick="showLogin()">登录</a>
                    <em>或</em>
                    <a onclick="showSignup()">注册</a>
                    <em>后可查看信息并购买</em>
                </p>
            </div>
        </c:if>
        <c:choose>
        <c:when test="${!empty cur_user}">
            <div class="item-contact">
                <div>
                    <div class="base-blue z-depth-1 attr">
                        <i class="iconfont"></i>
                    </div>
                    <div class="value">${seller.username}</div>
                </div>
                <div>
                    <div class="base-blue z-depth-1 attr">
                        <i class="iconfont"></i>
                    </div>
                    <div class="value">${seller.phone}</div>
                </div>
                <div>
                    <div class="base-blue z-depth-1 attr">
                        <i class="iconfont"></i>
                    </div>
                    <div class="value">${seller.qq}</div>
                </div>
            </div>
            <c:if test="${goodsExtend.goods.userId != cur_user.id}">
                <div class="item-public-info">
                    <div id="item-option">
                        <c:choose>
                            <c:when test="${goodsExtend.goods.status == 1 }">
                                <a onclick="order()" class="item-price">立即购买</a>
                            </c:when>
                            <c:otherwise>
                                <span>该商品已交易</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:if>

        </c:when>
            <c:otherwise>
                <div>该用户已被拉黑</div>
            </c:otherwise>
        </c:choose>
        <h1 class="item-pub-time">发布于 ${goodsExtend.goods.startTime}</h1>

    </div>

</div>

<div class="detail-box stark-components z-depth-1 row">
    <h1 class="title">商品详情</h1>
    <hr class="hr1" />
    <hr class="hr2" />
    <p class="section">${goodsExtend.goods.describle}</p>
    <p class="section"></p>
    <p class="section">
        联系我的时候，请说明是在江理校园二手工坊上看见的哦~
    </p>
</div>

<!--
描述：确认订单
-->
<div id="confirm-show" style="display: none;" class="login stark-components">
    <div class="publish-box z-depth-4" style="width: 680px;left: 35%;top: 20%;height: 350px;">
        <div class="row">
            <a onclick="order()">
                <img style="width:30px;height:30px;float:right" src="<%=basePath%>/img/close.png"/>
            </a>
    <form:form action="/order/subOrder" method="post" commandName="order" role="form">
        <input type="hidden" name="cost" value="${goodsExtend.goods.price }">
        <input type="hidden" name="goodsId" value="${goodsExtend.goods.id }">
        <input type="hidden" name="goodsName" value="${goodsExtend.goods.name }">
        <div style="float:left;margin:20px 0px 0px 10px;">
            <img style="width:300px;height: 250px;" src="<%=basePath%>upload/${goodsExtend.images[0].imgUrl}" />
        </div>
        <div style="float:right;height:30px;font-size: 18px;margin:40px 40px 0px 0px;">
            <p style="margin-bottom: 15px">收货人：${cur_user.username}</p>
            <p style="margin-bottom: 15px">电&nbsp;&nbsp;&nbsp;话：${cur_user.phone}</p>
            <p style="margin-bottom: 15px">地&nbsp;&nbsp;&nbsp;址：${cur_user.address}</p>
            <p style="margin-bottom: 15px">数量：1</p>
            <p style="margin-bottom: 15px">价格：￥${goodsExtend.goods.price }</p>
            <button type="submit" style="top:120%;margin-left:0px;" class="waves-effect waves-light btn login-btn red lighten-1">
                <i class="iconfont left"></i>
                <em>提交订单</em>
            </button>
        </div>

    </form:form>
        </div>
    </div>
</div>
</body>

</html>