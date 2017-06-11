<%@page import="com.google.common.collect.Lists"%>
<%@page import="shopping.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%
//        pageContext.setAttribute("message", "pageContext");
//        request.setAttribute("message", "request");
//        session.setAttribute("message", "session");
       application.setAttribute("message", "application");
       
       User user = new User();
       user.setUsername("enmuser");
       request.setAttribute("user", user);
       
       List<String> name = Lists.newArrayList();
       name.add("aaa");
       name.add("bbb");
       name.add("ddd");
       request.setAttribute("name", name);
     %>
  </head>
  
  <body>
   <!-- EL寻找从pageContext->request->session->application 谁先找到显示谁，没找到不显示 -->
     ${message}<br/>
     
     ${pageScope.message }<br/>
     ${requestScope.message }<br/>
     ${sessionScope.message}<br/>
     ${applicationScope.message }<br/>
     
     ${user.username }<br/>
     ${pageContext }***<br/>
     <!-- request session 对象不能直接获取 需要通过pageContext间接获取 -->
     ${request }<br/>
     ${session }<br/>
     ${application }<br/>
     
     ${pageContext.request }<br/>
     ${pageContext.session }<br/>
     ${pageContext.request.contextPath }<br/>
     
     ${user["username"] }<br/>
     
     ${name[2] }<br/>
     
     <!-- EL表达式的运算 -->
     ${1+2+3+4+5 }<br/>
     ${1==3 }<br/>
     ${1==2 ? "aaa" : "bbb" }<br/>
     
     <!-- EL表达式的比较 -->
     <!-- 字符串的比较 -->
     ${"aaa" eq "aaa" }<br/>
     <!-- 对象是否为空  空为true-->
     ${empty user }<br/>
     
     <!-- 访问请求参数  http://127.0.0.1:8081/HelloServlet/jsp/EL/EL.jsp?abc=1234567890 -->
     ${param.abc }
     
     <!-- 不行 -->
<%--      ${pageContext.application } --%>
     
  </body>
</html>
