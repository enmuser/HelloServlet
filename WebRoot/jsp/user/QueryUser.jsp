<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="user.servlet.together.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
   <%@ include file="/jsp/user/Top.jsp" %>
	<table border="1px">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>用户名</th>
				<th>用户邮箱</th>
				<th>性别</th>
				<th>薪水</th>
				<th>教育水平</th>
				<th>兴趣爱好</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
         <% 
            List<UserInfo> userInfos = (List<UserInfo>)request.getAttribute("userList");
            for(UserInfo user : userInfos){
         %>
			<tr>
				<td><%=user.getUserId() %></td>
				<td><%=user.getUsername() %></td>
				<td><%=user.getUserEmail() %></td>
				<td><%=user.getSex() %></td>
				<td><%=user.getSalary() %></td>
				<td><%=user.getEducation() %></td>
				<td><%=user.getHobbies() %></td>
				<td><a href='<%=request.getContextPath() %>/deleteUser.do?userId=<%=user.getUserId() %>'
					onclick='return confirm("确定要删除吗？")'>删除</a><a
					href='<%=request.getContextPath() %>/modifyUserById.do?userId=<%=user.getUserId() %>'>修改</a></td>
			</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>
</body>
</html>
