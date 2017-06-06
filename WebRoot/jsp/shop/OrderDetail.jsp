<%@page import="shopping.OrderDetailVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <style type="text/css">
       td,th{
         text-align: center;
       }
     </style>
  </head>
  
  <body>
    <h1>订单详情</h1>
    <hr/>
    <%
       List<OrderDetailVO> detailVOs = (List<OrderDetailVO>)request.getAttribute("OrderDetailVO");
     %>
        <%=detailVOs.get(0).getUsername()%>用户的<%=detailVOs.get(0).getOrderNo()%>订单<br/>
    -------------------------------------<br/>
    <table border="0">
       <tr style="c">
          <th>序号</th>
          <th>商品名称</th>
          <th>购买数量</th>
          <th>价格</th>
        </tr>
        <%
          for(OrderDetailVO detailVO : detailVOs){
         %>
        <tr>
           <td><%=detailVO.getItemId() %></td>
           <td><%=detailVO.getProductName() %></td>
           <td><%=detailVO.getNum() %></td>
           <td><%=detailVO.getItemprice() %></td>
        </tr>
        <%} %>
    </table>
    -------------------------------------<br/>
           共<%=detailVOs.get(0).getCount() %>条&nbsp;&nbsp;&nbsp;&nbsp;总计:<%=detailVOs.get(0).getTotalprice() %>元<br/>
    <a href="<%=request.getContextPath()%>/myOrder.order">返回</a>
  </body>
</html>
