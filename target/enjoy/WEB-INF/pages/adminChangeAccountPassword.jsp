<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="adminMenu.jsp"></jsp:include>

	<h1>Form change password cho account (service)</h1>
	<form
		action="${pageContext.request.contextPath}/admin/receiveChangeAccountPasswordRequest"
		method="GET">
		Account email <input type="text" name="email"> Change
		password: <input type="text" name="password">
		<div>
			<button type="submit">Submit</button>
		</div>
	</form>
	<div>
		<a>${message }</a>
	</div>
</body>
</html>