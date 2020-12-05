<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.top a {
	padding: 10px;
	border: 1px solid gray;
}
</style>
</head>
<body>
	<div class="top">
		<a
			href="${pageContext.request.contextPath}/admin/groups/netflix?loadNum=100&sort=0">Groups
			Netflix</a><a
			href="${pageContext.request.contextPath}/admin/groups/netflix/insufficientBalance?sort=3">Group
			Netflix đủ người chưa đủ tiền</a><a
			href="${pageContext.request.contextPath}/admin/groups/netflix/sufficientBalance">Groups
			Netflix đủ người đủ tiền Netflix</a> <a
			href="${pageContext.request.contextPath}/admin/addBalance">Cộng
			tiền cho user</a> <a
			href="${pageContext.request.contextPath}/admin/kickUserForm">Kick
			user</a> <a href="${pageContext.request.contextPath}/logout">Log out</a><a
			href="${pageContext.request.contextPath}/admin/changeAccountPassword">Change
			acc pw</a>

	</div>
</body>
</html>