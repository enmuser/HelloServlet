<%@page import="user.servlet.together.UserInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/jsp/el/function" prefix="function" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
    <%@ include file="/jsp/user/Top.jsp" %>
<%-- 	<% --%>
<!-- 	UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); -->	
<%-- 	%> --%>
<!-- 	<form -->
<%-- 		action='<%=request.getContextPath()%>/modifyUser.do?userId=<%=userInfo.getUserId()%>' --%>
<!-- 		method='post'> -->
<!-- 		name:<input type='text' name='userName' -->
<%-- 			value="<%=userInfo.getUsername()%>"><br /> password:<input --%>
<%-- 			type='text' name='password' value="<%=userInfo.getPassword()%>"><br /> --%>
<!-- 		email:<input type='email' name='userEmail' -->
<%-- 			value="<%=userInfo.getUserEmail()%>"><br /> salary:<input --%>
<%-- 			type='text' name='salary' value="<%=userInfo.getSalary()%>"><br /> --%>
<%-- 		sex:<input type='radio' name='sex' value='男' <%=getChecked("男",userInfo.getSex())%>>男 --%>
<%-- 		<input type='radio' name='sex' value='女' <%=getChecked("女",userInfo.getSex())%>>女<br /> --%>
<!-- 		education:<select name='education'> -->
<!-- 			<option value='0'>---请选择---</option> -->
<%-- 			<option value='高中' <%=getSelected("高中",userInfo.getEducation())%> >高中</option> --%>
<%-- 			<option value='专科' <%=getSelected("专科",userInfo.getEducation())%> >专科</option> --%>
<%-- 			<option value='本科' <%=getSelected("本科",userInfo.getEducation())%> >本科</option> --%>
<%-- 			<% String[] hB = userInfo.getHobbies().substring(1,userInfo.getHobbies().length()-1).split(","); %> --%>
<%-- 		</select><br /> hobbies:<input type='checkbox' name='hobby' value='打篮球' <%=getChecked("打篮球",hB)%>/>打篮球 --%>
<%-- 		<input type='checkbox' name='hobby' value='看电影' <%=getChecked("看电影",hB)%> />看电影 --%>
<%-- 		<input type='checkbox' name='hobby' value='旅行' <%=getChecked("旅行",hB)%> />旅行<br /> --%>
<!-- 		<input type='submit' value='ModifyUser' /> -->
<!-- 	</form> -->
<%-- 	<%! --%>
<!-- 		private String getChecked(String value, String[] hB)
// 		{
// 			if(Arrays.toString(hB).contains(value))
// 			{
// 				return "checked='checked'";
// 			}
// 			return "";
// 		}
	
// 		private String getSelected(String value, String education)
// 		{
// 			if(value.equals(education))
// 			{
// 				return "selected='selected'";
// 			}
// 			return "";
// 		}
	
// 		private String getChecked(String value, String sex)
// 		{
// 			if(value.equals(sex))
// 			{
// 				return "checked='checked'";
// 			}
// 			return "";
// 		} -->
<%-- 	 %> --%>

<!-- 用EL表达式替换所有的Java代码 -->

	<form
		action='${pageContext.request.contextPath }/modifyUser.do?userId=${userInfo.userId }'
		method='post'>
		name:<input type='text' name='userName'
			value="${userInfo.username}"><br /> password:<input
			type='text' name='password' value="${userInfo.password}"><br />
		email:<input type='email' name='userEmail'
			value="${userInfo.userEmail}"><br /> salary:<input
			type='text' name='salary' value="${userInfo.salary}"><br />
		sex:<input type='radio' name='sex' value='男' ${function:getCheckedSEX("男",userInfo.sex)}>男
		<input type='radio' name='sex' value='女' ${function:getCheckedSEX("女",userInfo.sex)}>女<br />
		education:<select name='education'>
			<option value='0'>---请选择---</option>
			<option value='高中' ${function:getSelected("高中",userInfo.education)} >高中</option>
			<option value='专科' ${function:getSelected("专科",userInfo.education)} >专科</option>
			<option value='本科' ${function:getSelected("本科",userInfo.education)} >本科</option>
			</select><br /> 
		hobbies:<input type='checkbox' name='hobby' value='打篮球' ${function:getCheckedHB("打篮球",userInfo.hobbies)}/>打篮球
		<input type='checkbox' name='hobby' value='看电影' ${function:getCheckedHB("看电影",userInfo.hobbies)} />看电影
		<input type='checkbox' name='hobby' value='旅行' ${function:getCheckedHB("旅行",userInfo.hobbies)} />旅行<br />
		<input type='submit' value='ModifyUser' />
	</form>
	
</body>
</html>
