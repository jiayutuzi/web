<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.Goods"%>
<%@ page import="com.dao.GoodsDao"%>

<html>
    <head>
    <head>
        <title>我的商品</title>
        <link rel="stylesheet" type="text/css" href="css/shopping.css"/>
    </head>
    <body>
		<div class="header">
			<h1>我的商品</h1>   
			<div class="login">
				<form action="LogoutServlet" method="post">
				<p id="user">未登录</p>
				<a href="Login.jsp" name="login">切换账号</a>
				<a href="register.jsp" name="register">注册</a>
                <a href="LogoutServlet">注销</a>
                <br>
                <input class="button"  type="button" onclick="window.location.href='GoodsServlet?action=mygoods'"value="我的商品"/>
                <input class="button"  type="button" onclick="window.location.href='CartServlet?action=mycart'" value="购物车"/><br>
                <input class="button"  type="button" onclick="window.location.href='LogServlet?action=show'" value="客户日志"/>
                <input class="button"  type="button" onclick="window.location.href='LogServlet?action=profit'" value="商品统计"/>     
				</form>
			</div>
		</div>
        
        <form action="LoginServlet" method="post">
            <input class="button"  type="button" onclick="window.location.href='ShowServlet'" value="商城"/>
            <hr>
            <%
                List<Goods> list=( List<Goods> )request.getAttribute("goods");
                if(list!=null){
                    for(Goods p:list){
            %>
            <div class="goods">
				<h2><%=p.Getname()%></h3>
				<p>商品id：<%=p.Getid()%></p>
				<p>价格：<%=p.Getprice()%></p>
				<p>数量: <%=p.Getnumber()%></p>
                <a href="GoodsServlet?action=gotoUpdate&id=<%=p.Getid()%>">修改</a>
                <a href="javaScript:deleteProduct(<%=p.Getid()%>);">删除</a>
                <hr>
			</div>
            <%
                    }
                }
            %>
        </form>
    </body>
</html>
<script> 
    window.onload=function()
    {
        var username ="当前用户："+'<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
        var success='<%=request.getParameter("success")%>';
        if(success=="true")
            alert("修改成功");
    }
</script>
<script>
    function deleteProduct(id) {
        if(confirm("是否删除数据")){
            location.href="GoodsServlet?action=delete&id="+id;
        }
    }
</script>