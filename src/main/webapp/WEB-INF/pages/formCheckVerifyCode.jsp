<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/login.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
</head>
<body>

	<jsp:include page="topNav.jsp"></jsp:include>

	<div class="login-page">
		<div class="label">
			<h1>Nhập mã khôi phục được gửi tới email.</h1>
		</div>
		<div class="warn">
			<h1>${message }</h1>
		</div>
		<div style="text-align: center; color: white; padding: 10px;">${email }</div>
		<div class="form">
			<s:url var="checkCode" value="/checkVerifyCode" />
			<form class="login-form" action="${checkCode }" method="GET">
				<input type="text" name="code" placeholder="Mã khôi phục" /> <input
					type="hidden" name="email" value="${email }">
				<button type="submit">Kiểm tra</button>

			</form>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementsByName("login")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("login")[0].style.color = "white";
	</script>
</body>
</html>