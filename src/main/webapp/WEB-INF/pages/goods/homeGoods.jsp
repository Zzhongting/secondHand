
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--
    描述：顶部
-->
<c:import url="../pagefiles/head.jsp"></c:import>
<!--
    描述：左侧导航条
-->
<c:import url="../pagefiles/left.jsp"></c:import>
</div>
<!--
    描述：右侧显示部分
-->
<div class="main-content">
    <!--
        描述：右侧banner（图片）部分
    -->
    <div class="slider-wapper">
        <div class="slider" style="height: 500px; touch-action: pan-y; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
            <ul class="slides" style="height: 470px;">
                <li class="active" style="opacity: 1;">
                    <a href="">
                        <div class="bannerimg">
                            <ul class="bannerul">
                                <p>亲爱的同学们：</p>
                                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到江西理工大学校园二手工坊。临近毕业季的你，</p>
                                <p>是否有太多的闲置与校友分享，为了追求更好的校园服务，</p>
                                <p class="text4">我们打造了一个全新的校园平台——<span>江理二手工坊</span>。</p>
                                <p>这里有更多的闲置分享，更自由的校园话题讨论，</br>你想要的，都在这里。</p>
                                <p  class="text6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;加入我们，你的大学，应更精彩。</p>
                            </ul>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </div>


</div>

    <c:forEach var="ca" items="${catelog}">
        <div class="index-title">
            <a href="/goods/catelog/${ca.id} ">${ca.name}</a>
            <hr class="hr1">
            <hr class="hr2">
        </div>
         <div class="waterfoo stark-components row">
             <div class="item-wrapper normal">
                 <c:forEach var="it" items="${map}">
                     <c:if test="${it.key==ca.id}">
                         <c:forEach items="${it.value}" var="item">
                             <div class="card col">
                                 <a href="<%=basePath%>goods/goodsId/${item.goods.id}">
                                     <div class="card-image">
                                         <img src="../upload/${item.images[0].imgUrl}" />
                                     </div>
                                     <div class="card-content item-price"><c:out value="${item.goods.price}"></c:out></div>
                                     <div class="card-content item-name">
                                         <p><c:out value="${item.goods.name}"></c:out></p>
                                     </div>
                                     <div class="card-content item-location">
                                         <p>江西理工大学</p>
                                         <p><c:out value="${item.goods.startTime}"></c:out></p>
                                     </div>
                                 </a>
                             </div>
                         </c:forEach>
                     </c:if>
                 </c:forEach>
             </div>
        </div>
    </c:forEach>
</body>
</html>