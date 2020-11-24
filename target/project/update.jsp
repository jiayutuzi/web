<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.Goods"%>
<%@ page import="com.dao.GoodsDao"%>
<html>
<head>
    <title>修改界面</title>
    <style type="text/css">
        body{
            background-repeat: no-repeat;
            background-position: center;
        }

    </style>
</head>
<%
    Goods good=(Goods)request.getAttribute("gotoupdate");
%>
<body>
<div style="text-align:center;margin-top: 120px">
    <form action="GoodsServlet?action=update" method="post">
        <table style="margin-left:40%">
            <caption>修改商品信息</caption>
            <tr>
                <td>商品名:</td>
                <td><input name="name" type="text" size="20" value="<%=good.Getname() %>"></td>
                <input type="hidden" name="id" value="<%=good.Getid()%>">
            </tr>
            <tr>
                <td>价格：</td>
                <td><input name="price" type="text" size="20" value="<%=good.Getprice() %>"></td>
            </tr>
             <tr>
                <td> 数量：</td>
                <td><input name="number" type="text" size="20" value="<%=good.Getnumber() %>"></td>
            </tr>
        </table>
        <input type="submit" value="修改">
        <input type="reset" value="取消" onclick="history.go(-1)">
    </form>
    <br>
    <a href="GoodsServlet?action=mygoods">返回</a>
    </form>
</div>

</body>
</html>
