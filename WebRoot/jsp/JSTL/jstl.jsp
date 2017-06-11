<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>
	<!-- Switch-Case -->
	<c:choose>
		<c:when test="${param.a>0 }">aaa</c:when>
		<c:when test="${param.a<0 }">bbb</c:when>
		<c:otherwise>
        ccc
       </c:otherwise>
	</c:choose>
    <br/>
	<!--    try-catch -->
	<c:catch var="error">
       <%
          int i = 1/0;
        %>
	</c:catch>
	${error.message }
	
	<br/>
<%-- 等价于 ${pageContext.request.contextPath } --%>
	<c:url value="/aaa"></c:url><br/>
	<!-- 将另一个页面加入当前页面 -->
<%-- 	<c:import url="/jsp/user/Login.jsp"></c:import><br/> --%>
<%-- 	<%@ include file="/jsp/user/Login.jsp" %><br/> --%>
<%-- 	<jsp:include page="/jsp/user/Login.jsp"></jsp:include><br/> --%>
   <!-- 重定向 -->
<%--   <c:redirect url="/jsp/user/Login.jsp"></c:redirect> --%>
   <!-- 转发 -->
   <jsp:forward page="/jsp/user/Login.jsp"></jsp:forward>
</body>
</html>
