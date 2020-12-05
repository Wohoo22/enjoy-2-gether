<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/login.css">
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
			<h1>Đăng nhập để sử dụng các tính năng.</h1>
		</div>

		<c:if test="${param.registered == 'true'}">
			<div class="warn">
				<h1>👌 ĐĂNG KÍ THÀNH CÔNG !</h1>
			</div>
		</c:if>

		<c:if test="${param.error == 'true'}">
			<div class="warn">
				<h1>⚠️ SAI THÔNG TIN ĐĂNG NHẬP !</h1>
			</div>
		</c:if>

		<div class="form">
			<form class="login-form" name='f'
				action="${pageContext.request.contextPath}/j_spring_security_check"
				method='POST'>

				<input type='text' name='username' value="${param.email }">
				<input type='password' name='password' value="${param.password }" />
				<button name="submit" type="submit" value="submit">Đăng
					nhập</button>
				<p class="message">
					Quên mật khẩu? <a
						href="${pageContext.request.contextPath}/forgetPassword">Khôi
						phục</a>
				</p>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementsByName("username")[0].placeholder = "Email";
		document.getElementsByName("password")[0].placeholder = "Mật khẩu";
	</script>
	<script type="text/javascript">
		document.getElementsByName("login")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("login")[0].style.color = "white";
	</script>
</body>
</html>