<%@page import="shopping.Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
     <h1>我的订单</h1>
     <hr/>
     <table border="1">
        <tr>
          <th>序号</th>
          <th>订单号</th>
        </tr>
        <%
           List<Order> orderList = (List<Order>)request.getAttribute("orderList");
           for(Order order : orderList) {
        %>
        <tr>
           <td><%=order.getId() %></td>
           <td><a href="<%=request.getContextPath()%>/OrderDetail.order?orderNo=<%=order.getOrderNo()%>"><%=order.getOrderNo() %></a></td>
        </tr>
        <%
          }
         %>
     </table>
     <a href="<%=request.getContextPath()%>/OfferingList.product">返回</a>
  </body>
</html>
