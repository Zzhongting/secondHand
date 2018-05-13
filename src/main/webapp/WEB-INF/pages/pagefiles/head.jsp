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
    <meta charset="utf-8" />
    <title>江理二手工坊</title>
    <link rel="stylesheet" href="<%=basePath%>css/index.css" />
    <script type="text/javascript" src="<%=basePath%>js/jquery.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/materialize.min.js" ></script>
    <script type="text/javascript" src="<%=basePath%>js/index.bundle.js" ></script>
    <link rel="stylesheet" href="<%=basePath%>css/materialize-icon.css" />
    <link rel="stylesheet" href="<%=basePath%>css/user.css" />
    <script>
        function showLogin() {
            if($("#signup-show").css("display")=='block'){
                $("#signup-show").css("display","none");
            }
            if($("#forget").css("display")=='block'){
                $("#forget").css("display","none");
            }
            if($("#login-show").css("display")=='none'){
                $("#login-show").css("display","block");
            }else{
                $("#login-show").css("display","none");
            }
        }
        function showSignup() {
            if($("#login-show").css("display")=='block'){
                $("#login-show").css("display","none");
            }
            if($("#forget").css("display")=='block'){
                $("#forget").css("display","none");
            }
            if($("#signup-show").css("display")=='none'){
                $("#signup-show").css("display","block");
            }else{
                $("#signup-show").css("display","none");
            }
        }
        function showForget(){
            if($("#signup-show").css("display")=='block'){
                $("#signup-show").css("display","none");
            }
            if($("#login-show").css("display")=='block'){
                $("#login-show").css("display","none");
            }
            if($("#forget").css("display")=='block'){
                $("#forget").css("display","none");
            }else{
                $("#forget").css("display","block");
            }
        }
        function ChangeName() {
            if($("#changeName").css("display")=='block'){
                $("#changeName").css("display","none");
            }else{
                $("#changeName").css("display","block");
            }
        }
        function checkPhone() {
            $.post(
                "/user/checkPhone/" + $("#phone").val(),
                {},
                function (data) {
                    if (data == 0) {
                        document.getElementById("span1").innerHTML = "<font color='red'>手机号不存在</font>";
                        $("#phone").focus();
                        $(".submit").unbind("click",
                            function (event) {

                            });
                    } else {
                        document.getElementById("span1").innerHTML = "<font color='green'>手机号✔</font>";
                    }
                });
        }

        function checkPhone1() {
            $.post(
                "/user/checkPhone/" + $("#phone1").val(),
                {},
                function (data) {
                    if (data == 1) {
                        document.getElementById("span2").innerHTML = "<font color='red'>手机号已被注册</font>";
                        $("#phone1").val("");
                        $("#phone1").focus();
                        $(".submit").unbind("click",
                            function (event) {

                            });
                    } else {
                        document.getElementById("span2").innerHTML = "<font color='green'>手机号✔</font>";
                    }
                });
        }
    </script>
<body ng-view="ng-view">
<div ng-controller="headerController" class="header stark-components navbar-fixed ng-scope">
    <nav class="white nav1">
        <div class="nav-wrapper">
            <a href="<%=basePath%>goods/homeGoods" class="logo">
                <em class="em1">江理</em>
                <em class="em2">二手工坊</em>
                <em class="em3">market</em>
            </a>
            <div class="nav-wrapper search-bar">
                <form ng-submit="search()" class="ng-pristine ng-invalid ng-invalid-required" action="/goods/search">
                    <div class="input-field">
                        <input id="search" name="str" placeholder="搜点什么吧..." style="height: 40px;"
                               class="ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required"/>
                        <label for="search" class="active">
                            <i onclick="search()" class="iconfont"></i>
                        </label>
                    </div>
                </form>
            </div>
            <ul class="right">
                <c:if test="${empty cur_user}">
                    <li class="publish-btn">
                        <button onclick="showLogin()" data-position="bottom" data-delay="50"
                                data-tooltip="需要先登录哦！" class="red lighten-1 waves-effect waves-light btn" data-tooltip-id="510d3084-e666-f82f-3655-5eae4304a83a"	>
                            我要发布</button>
                    </li>
                </c:if>
                <c:if test="${!empty cur_user}">
                    <li class="publish-btn">
                        <a href="/goods/publishGoods"  data-position="bottom" class="red lighten-1 waves-effect waves-light btn">我要发布</a>
                    </li>
                    <li>
                        <a href="/user/allGoods">我发布的商品</a>
                    </li>
                    <li>
                        <a>${cur_user.username}</a>
                    </li>
                    <li class="changemore">
                        <a class="changeMoreVertShow()">
                            <i class="iconfont"></i>
                        </a>
                        <div class="more-vert">
                            <ul class="dropdown-content">
                                <li><a href="/user/home">个人中心</a></li>
                                <li><a onclick="ChangeName()">更改用户名</a></li>
                                <li><a href="/user/logout">退出登录</a></li>
                            </ul>
                        </div>
                    </li>
                </c:if>
                <c:if test="${empty cur_user}">
                    <li>
                        <a onclick="showLogin()">会员登录</a>
                    </li>
                    <li>
                        <a href="/admin/logins">管理员入口</a>
                    </li>
                    <li>
                        <a onclick="showSignup()">注册</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</div>
<!--
    描述：登录
-->
<div ng-controller="loginController" class="ng-scope">
    <div id="login-show" class="login stark-components">
        <div class="publish-box z-depth-4">
            <div class="row">
                <a onclick="showLogin()">
                    <div class="col s12 title"></div>
                </a>
                <form:form action="/user/login" id="logins" method="post" commandName="user" role="form" onsubmit="checkPassword();" >

                    <div class="input-field col s12">
                        <input type="text" id="phone" name="phone" required="required" pattern="^1[0-9]{10}$" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" onblur="checkPhone();" />
                        <span id="span1" style="padding-left: 10px;"></span>
                        <label>手机号</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="password" id="password1" name="password" required="required" class="validate ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" />
                        <label>密码</label>
                        <a onclick="showForget()" class="forget-btn">忘记密码？</a>
                    </div>
                    <button type="submit" class="waves-effect waves-light btn login-btn red lighten-1">
                        <i class="iconfont left"></i>
                        <em>登录</em>
                    </button>
                    <div class="col s12 signup-area">
                        <em>没有账号？赶快</em>
                        <a onclick="showSignup()" class="signup-btn">注册</a>
                        <em>吧！</em>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<!--
    描述：注册
-->
<div ng-controller="signupController" class="ng-scope">
    <div id="signup-show" class="signup stark-components">
        <div class="publish-box z-depth-4">
            <div class="row">
                <a onclick="showSignup()">
                    <div class="col s12 title"></div>
                </a>
                <form:form action="/user/addUser" method="post" commandName="user" role="form">
                    <div class="input-field col s12">
                        <input type="text" id="username" name="username" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>昵称</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" id="phone1" name="phone" required="required" pattern="^1[0-9]{10}$" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" onblur="checkPhone1()"/>
                        <span id="span2" style="padding-left: 10px;"></span>
                        <label>手机号</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" id="QQ" name="QQ" required="required" pattern="^[1-9]{1}[0-9]{8|9}$" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>QQ</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="password" id="password" name="password" required="required" class="validate ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" />
                        <label>密码</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" id="address" name="address" required="required" class="validate ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" />
                        <label>地址</label>
                    </div>
                    <div ng-show="checkTelIsShow" class="col s12">
                        <button type="submit" class="waves-effect waves-light btn verify-btn red lighten-1">
                            <i class="iconfont left"></i>
                            <em>点击注册</em>
                        </button>
                    </div>
                    <div ng-show="checkTelIsShow" class="login-area col s12">
                        <em>已有账号？去</em>
                        <a onclick="showLogin()">登录</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<!-- 找回密码 -->
<div ng-controller="forgetController" class="ng-scope"  >
    <div id="forget" class="forget stark-components" style="display: none">
        <div class="publish-box z-depth-4">
            <div class="row">

                <div class="col s12 title">
                    <h1>找回密码</h1>
                </div>
                <form:form action="/user/findPassword" method="post" commandName="user" role="form">
                    <div class="input-field col s12">
                        <input type="text" name="username" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>用户名</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" name="phone" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>电话</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" name="password" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>新密码</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" name="repassword" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>确认新密码</label>
                    </div>
                    <div ng-show="checkTelIsShow" class="col s12">
                        <button class="waves-effect waves-light btn publish-btn red lighten-1">
                            <i class="iconfont left"></i>
                            <em>确认</em>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<!--更改用户名-->
<div ng-controller="changeNameController" class="ng-scope">
    <div id="changeName" class="change-name stark-components">
        <div class="publish-box z-depth-4">
            <div class="row">
                <div class="col s12 title">
                    <h1>修改用户名</h1>
                </div>
                <form:form action="/user/changeName" method="post" commandName="user" role="form">
                    <div class="input-field col s12">
                        <input type="text" name="username" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>修改用户名</label>
                    </div>
                    <div ng-show="checkTelIsShow" class="col s12">
                        <button class="waves-effect waves-light btn publish-btn red lighten-1">
                            <i class="iconfont left"></i>
                            <em>确认</em>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

