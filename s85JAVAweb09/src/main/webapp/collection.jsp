<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>收藏夹</title>
</head>
<body>
<table>
    <tr>
        <th>用户名</th>
        <th>商品编号</th>
    </tr>
    <c:forEach items="${list}" var="l">
        <tr>
            <td>${l.username}</td>
            <td>${l.goodsid}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
