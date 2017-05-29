<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
     <form action="<%=request.getContextPath()%>/addUser.do" method="post">
       name:<input type="text" name="userName" value=""><br/>
       password:<input type="password" name="password" value=""><br/>
       email:<input type="email" name="userEmail" value=""><br/>
       salary:<input type="text" name="salary" value=""/><br/>
       sex:<input type="radio" name="sex" value="男">男
           <input type="radio" name="sex" value="女">女<br/>
       education:<select name="education">
                  <option value="0">---请选择---</option>
                  <option value="高中">高中</option>
                  <option value="专科">专科</option>
                  <option value="本科">本科</option>
                  </select><br/>
       hobbies:<input type="checkbox" name="hobby" value="打篮球"/>打篮球
               <input type="checkbox" name="hobby" value="看电影"/>看电影
               <input type="checkbox" name="hobby" value="旅行"/>旅行<br/>
       <input type="submit" value="AddUser"/>  
    </form>
  </body>
</html>
