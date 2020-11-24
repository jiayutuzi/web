<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <style type="text/css">
        body{
            background-repeat: no-repeat;
            background-position: center;
        }

    </style>
</head>
<body>
<div style="text-align:center;margin-top: 120px">
    <form action="GoodsServlet" method="post">
        <table style="margin-left:40%">
            <caption>添加商品</caption>
            <tr>
                <td>商品名:</td>
                <td><input name="name" type="text" size="20"></td>
            </tr>
            <tr>
                <td>价格：</td>
                <td><input name="price" type="text" size="20"></td>
            </tr>
             <tr>
                <td> 数量：</td>
                <td><input name="number" type="text" size="20"></td>
            </tr>
        </table>
        <input type="submit" name = "addproduct" value="提交">
        <input type="reset" value="重置">
    </form>
    <br>
    <a href="ShowServlet">返回</a>
    </form>
</div>

</body>
</html>
