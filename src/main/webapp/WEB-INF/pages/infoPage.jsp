<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/infoPage.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
</head>
<body>
	<jsp:include page="topNav.jsp"></jsp:include>
	<div class="login-page">
		<div class="label">
			<h1>Thông tin liên hệ</h1>
		</div>
		<div class="form">
			<div class="link">
				<a href="https://www.facebook.com/profile.php?id=100014609199798"
					target="_blank">FACEBOOK ADMIN</a>
			</div>
			<br>
			<div class="link">
				<a href="https://www.facebook.com/enj0y2gether/" target="_blank">FACEBOOK
					FANPAGE</a>
			</div>
			<br>
			<div class="link">
				<a href="https://www.facebook.com/groups/1142656352781877"
					target="_blank">FACEBOOK GROUP</a>
			</div>
			<br>
			<div class="link">
				<a>0923559598</a>
			</div>
		</div>
		<div class="label">
			<h1>Hướng dẫn sử dụng</h1>
		</div>
		<div class="form" style="text-align: left;">
			<div class="info">
				<b>Bước 1: </b>Tham gia 1 nhóm hoặc tạo 1 nhóm của riêng bạn.
			</div>
			<div class="info">
				<b>Bước 2: </b>Đợi đến khi nhóm có đủ 5 thành viên.
			</div>
			<div class="info">
				<b>Bước 3: </b>Các thành viên nạp tiền vào hệ thống theo thông tin
				nạp tiền bên dưới.
			</div>
			<div class="info">
				<b>Bước 4: </b>Khi các thành viên trong nhóm đã nạp tiền đầy đủ, tài
				khoản Netflix sẽ được tạo và up lên nhóm.
			</div>
		</div>
		<div class="label">
			<h1>Hướng dẫn nạp tiền</h1>
		</div>
		<div class="form" style="text-align: left;">
			<div style="text-align: center;">
				<b style="color: #76b852;">NETFLIX:75k/tháng</b>
			</div>
			<div class="info">
				<b>Chuyển khoản: </b>0301000414406-VIETCOMBANK-Chi nhánh Hoàn Kiếm.
			</div>
			<div class="info">
				<b>Momo: </b>0923559598 (có thể liên lạc bằng số này).
			</div>
			<div class="info">
				<b>Nội dung chuyển tiền: </b>"username + email + enjoy2gether".
			</div>
			<div class="info">+ Lưu ý, tiền chuyển đến được admin trực tiếp
				kiểm tra chứ không dùng bot vì lí do bảo mật, nên các bạn chuyển
				tiền cho mình rồi chưa thấy cộng tiền hệ thống thì cứ đợi 1 lát là
				có nhé, mình kiểm tra nhiều lần trong ngày nên sẽ có sớm thôi.</div>
		</div>
	</div>
	<div class="credit">2020 © Designed by enjoy2gether</div>
	<script type="text/javascript">
		document.getElementsByName("infoPage")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("infoPage")[0].style.color = "white";
	</script>
</body>
</html>