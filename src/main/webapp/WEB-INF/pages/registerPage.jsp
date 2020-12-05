<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/register.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
<title>Enjoy2gether</title>
</head>

<body>
	<jsp:include page="topNav.jsp"></jsp:include>

	<div class="login-page">
		<div class="label">
			<h1>Đăng kí tài khoản.</h1>
		</div>

		<c:if test="${alert == 1}">
			<div class="warn">
				<h1>⚠️ ${message } !</h1>
			</div>
		</c:if>

		<div class="form">
			<s:url var="formUrl" value="/register" />
			<sf:form class="login-form" modelAttribute="form"
				action="${formUrl }" method="POST">
				<sf:input name="username " path="username" />
				<sf:input name="email " path="email" />
				<sf:input name="password " path="password" />
				<sf:input name="confirmPassword " path="confirmPassword" />

				<button type="submit" name="submit">Đăng kí</button>
			</sf:form>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementsByName("username")[0].placeholder = "Tên người dùng";
		document.getElementsByName("email")[0].placeholder = "Email";
		document.getElementsByName("password")[0].placeholder = "Mật khẩu";
		document.getElementsByName("confirmPassword")[0].placeholder = "Xác nhận mật khẩu";
		document.getElementsByName("password")[0].type = "password";
		document.getElementsByName("confirmPassword")[0].type = "password";
	</script>
	<script type="text/javascript">
		document.getElementsByName("register")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("register")[0].style.color = "white";
	</script>
</body>
</html>