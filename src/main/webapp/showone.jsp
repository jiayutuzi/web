<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.Goods"%>
<%@ page import="com.dao.GoodsDao"%>

<html>
    <head>
    <head>
        <title>商品界面</title>
        <link rel="stylesheet" type="text/css" href="css/shopping.css"/>
    </head>
    <%
        Goods good=(Goods)request.getAttribute("good");
        int temp=1;
    %>
    <body>
		<div class="header">
			<h1><%= good.Getname()%></h1>   
			<div class="login">
			<form action="GoodsServlet" method="post">
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
        
        <form action="CartServlet?action=addCart" method="post">
            <hr>
            <div class="good">
				<h3><%=good.Getname()%></h3>
                <input type = "hidden" name="name" value="<%=good.Getname()%>">  
				<p>商品id：<%=good.Getid()%></p>
                <input type="hidden" name="id" value="<%=good.Getid()%>">
				<p>价格：<%=good.Getprice()%></p>
                <input type="hidden" name="price" value="<%=good.Getprice()%>">
				<p>卖家：<%=good.Getprovider()%></p>
                <input type="hidden" name="provider" value="<%=good.Getprovider()%>">
				<p>剩余数量: <%=good.Getnumber()%></p>
                <div style="float:left">
					<h2>购买数量:</h2>
					<input name="number" type="text" size="20" value="<%=temp%>">
				</div>
			</div>
                <input class="button"  type="submit" value="加入购物篮"/>
            <hr>
        </form>
    </body>
</html>
<script> 
    window.onload=function()
    {
        var username ="当前用户:"+'<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
    }
</script>