<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/2
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>

    #bigDiv{
        margin: auto;
        width: 1000px;
        height: 400px;
        border: 1px solid silver;
    }
    .smallDiv{
        width:200px;
        height: 200px;
        border: 1px solid skyblue;
        float: left;
        margin-left: 35px;
        margin-top: 40px;
        text-align: center;
    }
    .wenZi{
        margin: auto ;
        width: max-content;
        height: fit-content;
        text-align: center
    }
    .login{
        margin: auto;
        width: 300px;
        height:200px ;

    }

</style>
<head>
    <title>淘宝商城</title>
</head>
<body>
<div id="bigDiv">
    <c:forEach items="${map.list}" var="goods">
        <div class="smallDiv">
            ${goods.goodsid}
            ${goods.goodsname}
            <a href="goods.do?p=findbyid&goodsid=${goods.goodsid}">
                <img title="查看详情" style="height: 150px;width: 180px" src="images/${goods.goodspic}">
            </a>
        </div>
    </c:forEach>
</div>
<div class="wenZi">
    共${map.count}条，当前${map.page}/${map.pageCount}页
    <a href="goods.do?p=fenye&page=1&size=${map.size}">首页</a>
    <a href="goods.do?p=fenye&page=${map.page-1}&size=${map.size}">上一页</a>
    <a href="goods.do?p=fenye&page=${map.page+1}&size=${map.size}">下一页</a>
    <a href="goods.do?p=fenye&page=${map.pageCount}&size=${map.size}">尾页</a>
    <a href="showCar.jsp">购物车</a>
</div>
<div class="login">
    <c:if test="${empty userinfo}" var="f">
        <a href="login.jsp">登录</a>|<a href="zhuce.jsp">注册</a>
    </c:if>
    <c:if test="${not f}">
        欢迎：${userinfo.username}! <a href="user.do?p=findbyusername&username=${userinfo.username}">收藏夹</a>
    </c:if>
</div>
</body>
</html>
