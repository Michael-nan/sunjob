<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>商品详情</title>
    <script src="${pageContext.request.contextPath}/js/fenye.js"></script>
</head>
<body>
<table>
    <tr>
        <th>商品编号</th>
        <th>商品名</th>
        <th>商品价格</th>
    </tr>
    <c:forEach items="${map.list}" var="goods">
    <tr>
        <td>${goods.goodsid}</td>
        <td>${goods.goodsname}</td>
        <td>${goods.goodsprice}</td>
        </c:forEach>
    </tr>
</table>

名称：<input type="text" id="goodsname" value="${map.goodsname}">
价格：<input type="text" id="bottom" value="${map.bottom}">-<input type="text" id="top" value="${map.top}">
<input type="button" value="查找" onclick="query()"><br>
<c:forEach begin="${map.page<=6?1:map.page-5}" end="${map.page<=6?(map.pageCount>10?10:map.pageCount):map.pageCount<=map.page+4?map.pageCount:map.page+4}" var="i">
    <c:if test="${i==map.page}" var="f">
        [${i}]
    </c:if>
    <c:if test="${not f}">
        <a href="goods.do?p=fenye&page=${i}&size=${map.size}&goodsname=${map.goodsname}&bottom=${map.bottom}&top=${map.top}">${i}</a>
    </c:if>

</c:forEach>
</body>
</html>
