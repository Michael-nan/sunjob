<%@page pageEncoding="utf-8" %>
<html>
<body>
<form method="post" action="user.do?p=regist">
    username:<input type="text" name="username"><br>
    password:<input type="text" name="password"><br>
    lovers:<input type="checkbox" name="lovers" value="吃">吃
    <input type="checkbox" name="lovers" value="喝">喝
    <input type="checkbox" name="lovers" value="玩">玩
    <input type="checkbox" name="lovers" value="乐">乐
    <input type="submit" value="regist">
</form>
</body>
</html>
