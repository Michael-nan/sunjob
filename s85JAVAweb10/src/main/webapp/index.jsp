<%@page pageEncoding="utf-8" %>
<html>
<body>
    部门：<select id="dep">
        <!--<option>国防部</option>-->

    </select>
</body>
</html>
<script>
    onload = function(){
        var request = new XMLHttpRequest();
        request.open("get" , "dep.do?p=findall");
        request.send(null);//参数

        request.onreadystatechange = function(){

            if(request.readyState==4 && request.status==200){
                var ret = request.responseText;
                var json = eval(ret);//普通字符串  -> json

               // alert(json.length);
                //   解析json数据
                for (var i= 0 ; i<json.length;i++){
                    var option = new Option(json[i].depname,json[i].depid);
                    document.getElementById("dep").append(option);
                }

            }
        }

    }



</script>


