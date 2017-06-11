<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
      <form action="<%=request.getContextPath() %>/FileUpload" method="post" enctype="multipart/form-data">
               名称：<input type="text" name="username" /><br/>
               文件：<input type="file" name="file" /><br/>
         <input type="submit" value="上传">
      </form>
      <%
         String message = (String)request.getAttribute("message");
         if(null != message)
         {
            out.print(message);
         }
       %>
  </body>
</html>
