<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<table>
    <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品数量</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${map}" var="m">
    <tr>
        <td>${m.key.goodsid}</td>
        <td>${m.key.goodsname}</td>
        <td><a href="javascript:void(0)" onclick="change('-' ,${m.key.goodsid} , this)">-</a><input type="text" value="${m.value}" id="num" size="2"><a href="javascript:void(0)" onclick="change('+',${m.key.goodsid} ,this)">+</a></td>
        <td><a href="goods.do?p=deletefromcar&goodsid=${m.key.goodsid}">删除</a></td>
    </tr>
        </c:forEach>
</table>
</body>
</html>
<script>
    function change(op,goodsid,a){
        if(op=="-"){
            var num = document.getElementById("num").value;
            if(num==1){
                if(confirm("真的要删除吗?")){
                    var request = new XMLHttpRequest();
                    request.open("get" , "goods.do?p=deletefromcarajax&goodsid="+goodsid);
                    request.send(null);
                    request.onreadystatechange = function(){
                        if(request.readyState==4 && request.status==200){
                            var ret = request.responseText;
                            if(ret==1){
                                a.parentNode.parentNode.parentNode.removeChild(a.parentNode.parentNode);
                            }
                        }
                    }
                }
            }else{
                var request = new XMLHttpRequest();
                request.open("get" , "goods.do?p=carnumjian&goodsid="+goodsid);
                request.send(null);
                request.onreadystatechange = function(){
                    if(request.readyState==4 && request.status==200){
                        var ret = request.responseText;
                        if(ret==1){
                            document.getElementById("num").value=document.getElementById("num").value-1;
                        }
                    }
                }

            }


        }else{
            var request = new XMLHttpRequest();
            request.open("get" , "goods.do?p=carnumjian&goodsid="+goodsid);
            request.send(null);
            request.onreadystatechange = function(){
                if(request.readyState==4 && request.status==200){
                    var ret = request.responseText;
                    if(ret==1){
                        document.getElementById("num").value=document.getElementById("num").value*1+1;
                    }
                }
            }

        }




    }

</script>




