
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/userInfo.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
</head>
<body>
	<jsp:include page="topNav.jsp"></jsp:include>

	<div class="login-page">
		<div class="label">
			<h1>Thông tin người dùng</h1>
		</div>
		<div class="warn">
			<a>Nếu bạn vừa chuyển tiền cho mình mà chưa thấy được cộng số dư
				ở đây thì đợi trong ngày là có nhé, mình kiểm tra nhiều lần trong
				ngày mà nên bạn yên tâm.</a>
		</div>
		<div class="form">
			<b>TÊN NGƯỜI DÙNG:</b><a> ${username }</a> <br> <b>EMAIL
				ĐĂNG NHẬP:</b><a> ${email }</a> <br> <b>SỐ DƯ:</b> <a>${balance }
				VNĐ</a>
		</div>
	</div>

	<div class="log-out">
		<a href="${pageContext.request.contextPath}/logout">ĐĂNG XUẤT</a>
	</div>

	<br>

	<script type="text/javascript">
		document.getElementsByName("userInfo")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("userInfo")[0].style.color = "white";
	</script>
</body>
</html>