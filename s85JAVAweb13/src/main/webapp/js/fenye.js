function query(){
    var goodsname = document.getElementById("goodsname").value;
    var bottom = document.getElementById("bottom").value ;
    var top = document.getElementById("top").value ;

    location = "goods.do?p=fenye&goodsname="+goodsname+"&bottom="+bottom+"&top="+top;
}