<%@page pageEncoding="UTF-8" %>
<html>
<body>
    部门：<select id="dep">

    </select>

</body>
</html>
<script>
    onload=function () {
        var request=new XMLHttpRequest();
        request.open("get","dep.do?p=findall");
        request.send(null);

       request.onreadystatechange=function () {

           if (request.readyState==4&&request.status==200){
               var ret=request.responseXML;
               var deps=ret.getElementsByTagName("dep");

              // alert(deps.length);
               for (var i = 0; i <deps.length; i++) {
                   var option=new Option(deps[i].getAttribute("depname"),deps[i].getAttribute("depid"));
                   //alert(option.value);
                   alert(option.text);
                   var option=option.text;
                   document.getElementById("dep").append(option);

               }
           }
       }
    }


</script>
