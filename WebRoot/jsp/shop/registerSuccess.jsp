<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
   <hl><font color="red">恭喜<%=request.getAttribute("userName")%>,注册成功</font></hl>
   <jsp:forward page="<%=request.getContextPath() %>/OfferingList.product"></jsp:forward>
  </body>
</html>
