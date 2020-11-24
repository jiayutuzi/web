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
    <body>
		<div class="header">
			<h1>电子商城</h1>   
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
        <form action="GoodsServlet?action=search" method="post">
            <table style="float:right;width:30%">
                <tr>
                    <td><input type="text" size="21" name="product"/></td>
                    <td><input class="button" type="submit" name="search" value="搜索"/></td>
                </tr>
            </table>
        </form>
        <form action="LoginServlet" method="post">
            <input class="button"  type="button" onclick="window.location.href='addgoods.jsp'" value="添加商品"/>
            <input class="button"  type="button" onclick="window.location.href='ShowServlet'" value="返回商城"/>
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
				<p>卖家：<%=p.Getprovider()%></p>
				<p>数量: <%=p.Getnumber()%></p>
                <a style="float:right"href="GoodsServlet?action=showone&id=<%=p.Getid()%>">查看详情</a>
			</div>
            <hr>
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
        var username ="当前用户:"+'<%=request.getParameter("username")%>';
        var user=document.getElementById("user");
        user.innerHTML=username;
    }
</script>