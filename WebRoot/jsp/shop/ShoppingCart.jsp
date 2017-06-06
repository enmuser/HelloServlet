<%@page import="shopping.ShoppingCartVO"%>
<%@page import="shopping.Item"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
       function deleteItem()
       {
          var input = document.getElementsByTagName("input");
          var ids = '';
          for(var item in input)
          {
             if(input[item].type=="checkbox" && input[item].checked == true && input[item].value != 'on')
             {
               ids +=input[item].value+",";
             }
          }
          var del = document.getElementById("del");
          if(ids.length >=2)
          {
             del.href = '<%=request.getContextPath()%>/ClearCartItemByIDs.car?ids='+ids;
          }
          else
          {
             document.getElementById("mas").innerHTML = '你还没有选择商品,不能删除!';
          }
          
       }
       function chooseAll()
       {
          var all = document.getElementById("all");
          var input = document.getElementsByTagName("input");
          for(var item in input)
          {
             if(input[item].type=="checkbox")
             {
                input[item].checked = all.checked;
             }
          }
       }
       function settlement()
       {
          var input = document.getElementsByTagName("input");
          var ids = '';
          for(var item in input)
          {
             if(input[item].type=="checkbox" && input[item].checked == true && input[item].value != 'on')
             {
               ids +=input[item].value+",";
             }
          }
          var settlement = document.getElementById("settlement");
          
          if(ids.length >=2)
          {
             settlement.href = '<%=request.getContextPath()%>/Order.car?ids='+ids;
          }
          else
          {
             document.getElementById("mas").innerHTML = '要选择商品后,才能结算哦!';
          }
          
       }
    </script>
  </head>
  
  <body>
     <%!double totalPrice = 0.0; %>
     <h1>我的购物车</h1><a href="<%=request.getContextPath()%>/OfferingList.product">返回</a>
     <table border="1">
       <tr>
         <th><input type="checkbox" id="all" onclick="chooseAll()">全选</th>
         <th>序号</th>
         <th>商品</th>
         <th>数量</th>
         <th>价格</th>
         <th>操作</th>
       </tr>
       <%
           ShoppingCartVO cartVO = (ShoppingCartVO)request.getSession().getAttribute("Cart");
           if(cartVO != null){
           Map<Integer,Item> map = cartVO.getItems();
           Set<Integer> keys = map.keySet();
           totalPrice = 0.0;
           for(Integer key : keys)
           {
              Item item =map.get(key);
              totalPrice += item.getNum()*item.getProduct().getPrice();
        %>
       <tr>
         <td><input type="checkbox" value="<%=key %>"/></td>
         <td><%=key %></td>
         <td><%=item.getProduct().getName() %></td>
         <td>
           <input type="text" value="<%=item.getNum() %>" size="3" style="border:0;">
           <a href="<%=request.getContextPath()%>/addNum.car?productId=<%=key%>"><input type="button" value="+"/></a><a href="<%=request.getContextPath()%>/reduceNum.car?productId=<%=key%>"><input type="button" value="-"/></a>
         </td>
         <td><%=item.getProduct().getPrice()*item.getNum()%></td>
         <td>
           <a href="<%=request.getContextPath()%>/ClearCartItemByID.car?productId=<%=key %>" onclick="return confirm('是否确认删除');">删除</a>
           <a href="#" name="modify">修改</a>
         </td>
       </tr>
       <% } 
         }
       %>
     </table>
 _____________________________________________________________________________________________________________<br/>
              总计:<%=totalPrice %>元
      <a id="del" href="javascript:;" onclick="deleteItem()">删除选中项</a>
      <a href="<%=request.getContextPath()%>/ClearCart.car">清空购物车</a>
      <a href="<%=request.getContextPath()%>/OfferingList.product">继续购物</a>
      <a id="settlement" href="javascript:;" onclick="settlement()">结算</a><br/>
      <label><font id="mas" color="red"></font></label>
  </body>
</html>
