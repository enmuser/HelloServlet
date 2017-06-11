<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jsp/jstl/myTag" prefix="myTag" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
    <myTag:myTag num="10" info="Hello,MyTag"/>
    <myTag:LoginFace action="${pageContext.request.contextPath }" method="post"/>
    <myTag:TimeFormat targetFormat="yyyy年MM月dd日" srcFormat="yyyy-MM-dd" srcTime="2017-05-20"/>
  </body>
</html>
