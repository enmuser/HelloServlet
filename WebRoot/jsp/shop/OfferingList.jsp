<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="shopping.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>OfferingList</title>
  </head>
  <body>
    <h1>商品列表</h1>
    <%
       String username = (String)request.getSession().getAttribute("userName");
       if(null == username){
     %>
    <a href="<%=request.getContextPath()%>/jsp/shop/UserLogin.jsp">登录</a>
    <a href="<%=request.getContextPath()%>/jsp/shop/UserRegister.jsp">注册</a>
    <%}else{ %>
     <label>欢迎<font color="red"><%=username%></font></label>
     <a href="<%=request.getContextPath()%>/logOut.user">注销</a>
     <a href="<%=request.getContextPath()%>/myOrder.order">我的订单</a>
    <%} %>
    <table border="1">
       <tr>
        <th>序号</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品简介</th>
        <th>操作</th>
       </tr>
       <% 
          List<Product> products = (List<Product>)request.getAttribute("productList");
          for(Product product : products){
        %>
       <tr>
        <td><%=product.getId() %></td>
        <td><%=product.getName() %></td>
        <td><%=product.getPrice() %>￥/kg</td>
        <td><%=product.getDescription() %></td>
        <th><a href="<%=request.getContextPath()%>/addShoppingCart.car?productId=<%=product.getId()%>">加入购物车</a></th>
       </tr>
       <%} %>
       
    </table>
    <%
       String message = (String)request.getAttribute("message");
       if(null != message){
     %>
       <label><font color="red"><%=message%></font></label>
     <%} %>
    <h1><a href="<%=request.getContextPath()%>/jsp/shop/ShoppingCart.jsp">查看购物车</a></h1>
  </body>
   
</html>
