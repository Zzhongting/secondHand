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
    <title>个人设置</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js" ></script>
    <link rel="stylesheet" href="../css/font-awesome.min.css" />
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
            <div class="basic">
                <form:form action="/user/updateInfo" method="post" commandName="user" role="form" onsubmit="return checkForm();">
                    <h1>完善与修改个人信息</h1><hr />
                    <input type="hidden" id="id" name="id" value="${cur_user.id}"/>
                    <input type="hidden" id="password" name="password" value="${cur_user.password}"/>
                    <input type="hidden" id="goodsNum" name="goodsNum" value="${cur_user.goodsNum}"/>
                    <input type="hidden" id="power" name="power" value="${cur_user.power}"/>
                    <input type="hidden" id="lastLogin" name="lastLogin" value="${cur_user.lastLogin}"/>
                    <div class="changeinfo">
                        <span>昵称：</span>
                        <input class="in_info" type="text" id="username" name="username" placeholder="请输入昵称" value="${cur_user.username}"/>
                    </div><hr />
                    <div class="changeinfo">
                        <span>开通时间：</span>
                        <input class="in_info" type="text" id="createAt" name="createAt" value="${cur_user.createAt}" readonly="true"/>
                    </div><hr />
                    <div class="changeinfo">
                        <span>手机号码：</span>
                        <input class="in_info" type="text" id="phone" name="phone" value="${cur_user.phone}" onblur="phoneCheck();"/>
                        <span id="span1"></span>
                    </div><hr />
                    <div class="changeinfo">
                        <span>QQ：</span>
                        <input class="in_info" type="text" id="qq" name="qq" placeholder="请输入QQ" value="${cur_user.qq}"/>
                    </div>
                    <div class="changeinfo">
                        <span>地址：</span>
                        <input class="in_info" type="text" id="address" name="address" placeholder="请输入地址" value="${cur_user.address}"/>
                    </div>
                    <input type="submit" class="setting-save" value="保存修改信息" />
                </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    function phoneCheck() {
        $.post(
            "/user/phoneCheck/" + $("#phone").val(),
            {},
            function (data) {
                if (data == 1) {
                    document.getElementById("span1").innerHTML = "<font color='red'>手机号已被使用</font>";
                    $("#phone").focus();
                    $(".submit").unbind("click",
                        function (event) {

                        });
                }
                if(data == 0){
                    document.getElementById("span1").innerHTML = "<font color='green'>手机号✔</font>";
                }
            });
    }

    function checkForm() {
        var username = $("#username").val();
        if (username == null || username == '') {
            alert("姓名不能为空!");
            return false;
        }
        var phone = $("#phone").val();
        if (phone == null || phone == '') {
            alert("手机号不能为空!");
            return false;
        }

        var qq = $("#qq").val();
        if (qq == null || qq == '') {
            alert("qq不能为空!");
            return false;
        }
        var address = $("#address").val();
        if (address == null || address == '') {
            alert("地址不能为空!");
            return false;
        }

        }
</script>
</body>
</html>