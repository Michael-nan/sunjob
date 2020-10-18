<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/21
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示所有信息</title>
</head>
<body>
<%
    Map map = (Map)request.getAttribute("map");//取值
    List<UserInfo> list = (List<UserInfo>) map.get("list");
%>
<table border="1" bgcolor="#7fffd4">
    <tr>
        <th>用户名</th>
        <th>密码</th>
        <th></th>
    </tr>
<%for(UserInfo userinfo : list){%>
    <tr>
        <td><%=userinfo.getUsername()%></td>
        <td><%=userinfo.getPassword()%></td>
        <td><a href="user.do?p=deletebyusername&username=<%=userinfo.getUsername()%>">删除</a></td>
    </tr><%}%>
</table>
<a href="user.do?p=fenye&page=1&size=<%=map.get("size")%>">首页</a>
<a href="user.do?p=fenye&page=<%= (Integer)map.get("page")-1 %>&size=<%=map.get("size")%>">上一页</a>
<a href="user.do?p=fenye&page=<%= (Integer)map.get("page")+1 %>&size=<%=map.get("size")%>">下一页</a>
<a href="user.do?p=fenye&page=<%= (Integer)map.get("pageCount") %>&size=<%=map.get("size")%>">尾页</a>
<input type="text"  size="1" value="<%=map.get("page")%>" id="page">/<%= map.get("pageCount")%><input type="button"  value="前往" onclick="go()">

</body>
</html>
<script>
    function go() {
        var page=document.getElementById("page").value;
        location="user.do?p=fenye&page="+page+"&size=<%=map.get("size")%>";

    }
</script>