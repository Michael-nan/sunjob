<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
<c:forEach items="${list}" var="pic">
    <img style="height: 200px;width: 200px" src="images/${pic.pname}">
</c:forEach><br>
${goods.goodsid}
${goods.goodsname}
${goods.goodscount}
${goods.goodsprice}
${goods.goodsinfo}
<input type="text" id="num" size="3"><a href="javascript:void(0)" onclick="addCar(${goods.goodsid})">加入购物车</a>
<a href="user.do?p=addCollection&goodsid=${goods.goodsid}">收藏</a>
</body>
</html>
<script>
    function addCar(goodsid){
        var num = document.getElementById("num").value;
        location = "goods.do?p=addCar&goodsid="+goodsid+"&num="+num;
    }
</script>