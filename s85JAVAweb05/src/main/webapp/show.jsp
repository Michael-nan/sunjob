<%@ page import="java.util.List" %>
<%@ page import="com.pojo.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/15
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示所有信息</title>

</head>
<body>
<% List<UserInfo> list= (List<UserInfo>) request.getAttribute("list");%>
<table border="1" bgcolor="aqua">
<tr>
    <th>账号</th>
    <th>密码</th>
</tr>
    <%for(UserInfo userInfo : list){%>
    <tr>
        <td><%= userInfo.getUsername() %></td>
        <td><%= userInfo.getPassword()%></td>
    </tr>
    <%}%></table>
</body>
</html>
