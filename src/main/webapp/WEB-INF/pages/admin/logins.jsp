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
<<<<<<< HEAD
    <base href="<%=basePath%>">
    <title>江理二手工坊</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
</head>
<style type="text/css">

    #bd{
        margin:0 auto;
        width: 1355px;
        height: 640px;
        background: url("../upload/20180506144940.jpg");
        background-size:100% 100%;
        background-repeat:no-repeat;
    }
    #bl{
        padding: 200px  500px 0 500px;

    }
    #bl p{
        font-size:2em;
        color:lightseagreen;
        font-family: '楷体';
        text-align: center;
        padding: 0 0 10px 0;
    }
    #bl li{
        list-style: none;
        margin:30px 0;
        line-height: 30px;
        font-size: 15px;

    }

    input{
        border-radius: 0.5em;
        border-color: gray;
        background: white;
    }
    .demo {
        display:inline;
        line-height: 20px;
        width: 80px;
        text-align: center;
        margin:10px 0 0 60px;
        background: gray;
        font-size: 16px;
        -moz-box-shadow: 0 0 5px #343434;
        -webkit-box-shadow: 0 0 5px #fff;
        box-shadow: 0 0 5px #343434;
        color: #fff;
        font-weight: bold;
        padding: 5px 10px;
        text-decoration: none;
        text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
        border-radius: 1em;
        float: left;
    }
</style>
<script>
    function checkID() {
        $.post(
            "/user/checkID/" + $("#Id").val(),
            {},
            function (data) {
                if (data == 0) {
                    document.getElementById("span1").innerHTML = "<font color='red'>管理员不存在</font>";
                    $("#Id").val("");
                    $("#Id").focus();
                    $(".submit").unbind("click",
                        function (event) {

                        });
                } else {
                    document.getElementById("span1").innerHTML = "<font color='green'>管理员✔</font>";
                }
            });
    }
    function checkID() {
        $.post(
            "/user/checkID/" + $("#Id").val(),
            {},
            function (data) {
                if (data == 0) {
                    document.getElementById("span1").innerHTML = "<font color='red'>管理员不存在</font>";
                    $("#Id").val("");
                    $("#Id").focus();
                    $(".submit").unbind("click",
                        function (event) {

                        });
                } else {
                    document.getElementById("span1").innerHTML = "<font color='green'>管理员✔</font>";
                }
            });
    }
</script>
<body ng-view="ng-view">

    <div id="bd" >
        <form:form action="/admin/login" id="logins" method="post" commandName="admini" role="form">
            <ul id="bl">
                <p><strong>管理员登录</strong></p>
                <li>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;
                    <input type="text" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" size="28" id="Id" name="id" onblur="checkID()"></li>
                <span id="span1" style="padding-left: 10px;"></span>
                <li>用&nbsp;户&nbsp;名&nbsp;&nbsp;&nbsp;<input type="text" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" size="28" name="name"></li>
                <li>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;<input type="password" required="required" class="validate ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" size="28" name="password"></li>
                <c:if test="${Msg != null}">
                    <span style="color:red;">${Msg}</span>
                </c:if>
                <li><input type="submit" class="demo"  value="登录">
                    <a href="<%=basePath%>goods/homeGoods" class="demo">返回</a>
                </li>
            </ul>
        </form:form>
    </div>

=======
    <title>江理二手工坊</title>
</head>
<body ng-view="ng-view">

<div ng-controller="loginController" class="ng-scope">
    <div id="login-show" class="login stark-components">
        <div class="publish-box z-depth-4">
            <div class="row">
                <form:form action="/admin/login" id="logins" method="post" commandName="admini" role="form">

                    <div class="input-field col s12">
                        <input type="text" name="id" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched" />
                        <label>编号<span id="errorMsg"></span> </label>
                    </div>
                    <div class="input-field col s12">
                        <input type="text" name="name" required="required" class="validate ng-pristine ng-empty ng-invalid ng-invalid-required ng-valid-pattern ng-touched"/>
                        <label>用户名</label>
                    </div>
                    <div class="input-field col s12">
                        <input type="password" name="password" required="required" class="validate ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required" />
                        <label>密码</label>
                    </div>
                    <button type="submit" class="waves-effect waves-light btn login-btn red lighten-1">
                        <i class="iconfont left"></i>
                        <em>登录</em>
                    </button>
                </form:form>
            </div>
        </div>
    </div>
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
</div>
</body>
</html>