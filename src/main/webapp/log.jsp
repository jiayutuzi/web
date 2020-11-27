<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.Log"%>
<%@ page import="com.dao.LogDao"%>

<html>
    <head>
    <head>
        <title>客户日志</title>
        <link rel="stylesheet" type="text/css" href="css/shopping.css"/>
    </head>
    <body>
		<div class="header">
			<h1>客户日志</h1>   
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
                List<Log> list=( List<Log> )request.getAttribute("log");
                if(list!=null){
                    for(Log p:list){
                        if(p.Getstatus().equals("购买")){
            %>
            <div class="log">
				<p><%=p.Gettime()%>:用户 <%=p.Getuser()%>购买了商品<%=p.Getname()%>,商品id为：<%=p.Getid()%>,件数:<%=p.Getnumber()%>,商品单价：<%=p.Getprice()%> <a href="javaScript:deleteProduct('<%=p.Gettime()%>+<%=p.Getid()%>');">删除</a></p>
                
			</div>
            <%}
            else{%>
            <div class="log">
				<p><%=p.Gettime()%>:用户 <%=p.Getuser()%>浏览了商品<%=p.Getname()%>,商品id为：<%=p.Getid()%> <a href="javaScript:deleteProduct('<%=p.Gettime()%>+<%=p.Getid()%>');">删除</a></p>
                
			</div>
            <%          }
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
    function deleteProduct(pram) {
        var p=pram.split("+");
        if(confirm("是否删除数据")){
            location.href="LogServlet?action=delete&time="+p[0]+"&page=log.jsp"+"&id="+p[1];
        }
    }
</script>