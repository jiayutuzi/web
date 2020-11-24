<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录失败</title>
</head>
<body>
<h1>登录/注册失败</h1>
<hr>
<a href="Login.jsp">重新登录</a>
<br>
<a href="register.jsp">注册</a>
</body>
</html>
<script> 
//取出传回来的参数error并与yes比较
  var errori ='<%=request.getParameter("error")%>';
  if(errori=='yes'){
    alert("用户名或密码错误!");
  }
  else
    alert("用户名已存在！")
</script>
