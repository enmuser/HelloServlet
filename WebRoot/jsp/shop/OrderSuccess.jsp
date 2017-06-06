<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h1>订单提交成功</h1>
     <hr>
<%--      <% --%>
<!-- String orderNo = (String)request.getAttribute("orderNo"); -->
<%--       %> --%>
     <h1>订单生成成功!订单号:<%=request.getSession().getAttribute("orderNo") %></h1>
     <a href="<%=request.getContextPath()%>/OfferingList.product">继续购物</a>
     <%
        request.getSession().removeAttribute("orderNo");
      %>
  </body>
</html>
