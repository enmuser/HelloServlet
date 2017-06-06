<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
     <h1>用户注册</h1>
    <form action="<%=request.getContextPath() %>/register.user" method="post">
             用户名:<input type="text" name="username"><br/>
             密&nbsp;码:<input type="password" name="password"><br/>
            手机号:<input type="text" name="userphoneNumber"><br/>
            邮&nbsp;箱:<input type="email" name="userEmail"><br/>
            地&nbsp;址:<input type="text" name="userAddress"><br/>
      <input type="submit" value="注册">
    </form>
  </body>
</html>
