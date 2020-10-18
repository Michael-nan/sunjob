<%@page pageEncoding="UTF-8" %>
<html>
<body>
<form method="post" action="test.do">
    用户名：<input type="text" id="username" name="username" onblur="check()"><span id="flag"></span><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="注册">

</form>
</body>
</html>
<script>
    function check() {
        var username=document.getElementById("username").value;

        var request=new XMLHttpRequest();
        request.open("get","test.do?p=check&username="+username);//   第三个参数    是否异步
        request.send(null);//  如果用同步  这两几句话下移到末尾

        request.onreadystatechange=function () {

            if (request.readyState==4&&request.status==200){//  请求发送    接收了正确的响应
                var ret=request.responseText;//接收服务器返回的结果
                //alert(ret);
                if (ret==2){
                    document.getElementById("flag").innerHTML="√";
                }else {
                    document.getElementById("flag").innerHTML="×";

                }
            }

        }

    }


</script>
