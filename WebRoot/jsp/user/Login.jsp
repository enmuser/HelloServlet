<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <script type="text/javascript">
        function changeImage()
        {
           document.getElementById("image").src ="<%=request.getContextPath()%>/VerifyCode?id="+new Date().getTime()
           return false;
        }
     </script>
  </head>
  <body>
     <%@ include file="/jsp/user/count.jsp" %>
    <div >
      <form style="margin: 100px 480px;" action="<%=request.getContextPath() %>/login.do" method="post">
                       用户名:<input type="text" name="loginName"><br/>
                         密码:<input type="text" name="loginpwd"><br/>
                      验证码:<input id="code" type="text" name="verifyCode" onblur="checkVerifyCode(<%=request.getSession().getAttribute("verifyCode")%>)">
              <a onclick="changeImage()"><img id="image" src="<%=request.getContextPath()%>/VerifyCode"></a>
             <a href="javascript:void(0)" onclick="return changeImage()">看不清？换一张</a><br/>
           <input type="submit" value="登录">
         <%
         String message = (String)request.getAttribute("message"); 
         if(null != message){
         %>
           <font color="red"><%=message%></font>             
         <%} %>
      </form>
     </div>
  </body>
</html>
