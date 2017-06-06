<%@page import="shopping.User"%>
<%@page import="shopping.Item"%>
<%@page import="shopping.ShoppingCartVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h1>订单详情</h1>
    <%
        User user = (User)request.getAttribute("user");
        String ids = (String)request.getAttribute("ids");
     %>
          用户名：<%=user.getUsername() %><br/>
          电话：<%=user.getPhone() %><br/>
          地址：<%=user.getAddress() %><br/>
          邮箱：<%=user.getUseremail() %><br/>
     <table border="1">
       <tr>
         <th>序号</th>
         <th>商品</th>
         <th>数量</th>
         <th>价格</th>
       </tr>
       <%
           ShoppingCartVO cartVO = (ShoppingCartVO)request.getSession().getAttribute("Cart");
           if(cartVO != null){
           Map<Integer,Item> map = cartVO.getItems();
           Set<Integer> keys = map.keySet();
           for(Integer key : keys)
           {
             if(ids.contains(String.valueOf(key)))
             {
              Item item =map.get(key);
        %>
       <tr>
         <td><%=key %></td>
         <td><%=item.getProduct().getName() %></td>
         <td><%=item.getNum() %></td>
         <td><%=item.getProduct().getPrice()*item.getNum() %></td>
       </tr>
       <% }
          } 
         }
       %>
     </table>
_____________________________________________________________________________________________________________<br/>
       <%
           ShoppingCartVO cartVO1 = (ShoppingCartVO)request.getSession().getAttribute("Cart");
           Map<Integer,Item> map1 = cartVO.getItems();
           Set<Integer> keys1 = map1.keySet();
           double totalPrice = 0.0;
           for(Integer key : keys1)
           {
             if(ids.contains(String.valueOf(key))){
              Item item1 =map1.get(key);
              totalPrice += item1.getNum()*item1.getProduct().getPrice();
              }
           }
        %>        
              总计:<%=totalPrice%>元<br/>
      <a href="<%=request.getContextPath()%>/submitOrder.car?ids=<%=ids%>">提交订单</a>
      <a href="<%=request.getContextPath()%>/jsp/shop/ShoppingCart.jsp">返回购物车</a>
  </body>
</html>
