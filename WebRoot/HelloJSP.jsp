<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
   <% for(int i=0 ; i<100;i++) {%>
    <hl><font color="red">Hello JSP</font></hl>
    <%} %>
    
    <%
       Date date = new Date();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String strDate = dateFormat.format(date);
     %>
     <h1><%=strDate%></h1>
  </body>
</html>
