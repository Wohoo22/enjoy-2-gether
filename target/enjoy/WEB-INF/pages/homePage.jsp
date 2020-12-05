<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/homePage.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
</head>
<body>
	<jsp:include page="topNav.jsp"></jsp:include>
	<br>
	<div class="container">
		<div class="label">
			<h1>ENJOY2GETHER</h1>
			<a>Nền tảng chia sẻ - mua chung tài khoản Netflix.</a>
		</div>
		<br>
		<div class="container">
			<div class="group"
				style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
				<div class="name">ĐIỂM NỔI BẬT</div>
				<div class="users">+ Sự bảo mật, tin tưởng cho người dùng.</div>
				<div class="users">+ Thanh toán dễ dàng, nhanh chóng.</div>
				<div class="users">+ Đặt lợi ích người dùng lên hàng đầu.</div>
				<div class="users">+ Admin luôn có mặt để quản lý và hỗ trợ.</div>

			</div>

			<div class="group"
				style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
				<div class="name">
					<a>MỨC GIÁ</a>
				</div>
				<div class="users">
					<b>NETFLIX: </b>75k/slot/tháng
				</div>
				<div class="users">+ Tài khoản Netflix được đăng kí hoàn toàn
					chính thống, không thay đổi hàng tháng</div>
				<div class="users">+ Người dùng chỉ việc nạp tiền vào hệ thống
					và dùng. Còn mọi việc cứ để admin lo.</div>
			</div>
			<div class="group"
				style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24); width: 100%; border-top: 10px solid #76b852; height: auto">
				<div class="name">
					<a>GIỚI THIỆU</a>
				</div>
				<div class="users" style="border-bottom: 1px solid #76b852;">Nhận
					thấy tình trạng bán tài khoản trial, tài khoản dùng thẻ ăn cắp của
					Netflix với giá siêu rẻ tràn lan trên Facebook kéo theo rất nhiều
					rủi ro cho người mua nên mình tạo ra hệ thống với sự ưu tiên hàng
					đầu là bảo đảm cho người dùng, nhằm kết nối những người có nhu cầu
					xem Netflix đơn lẻ nhưng không có bạn mua chung.</div>
				<div class="users">
					<b>Cơ chế hoạt động của website: </b>Người dùng có thể tạo
					nhóm/tham gia nhóm có sẵn. Khi nhóm đã đủ số lượng người (nhóm
					Netflix thì 5 người/nhóm = 5 slot/tài khoản) và các thành viên của
					nhóm đã nạp tiền vào hệ thống, admin sẽ tạo tài khoản Netflix và
					update lên hệ thống cho nhóm ấy.
				</div>
				<div class="users" style="border-bottom: 1px solid #76b852;">
					<b>Lưu ý: </b>Mình chỉ nhận nạp tiền đúng 1 tháng cho các bạn, mình
					k nạp nhiều hơn trong 1 lượt, hết hạn các bạn muốn xem tiếp thì nạp
					tiếp, điều này nhằm bảo đảm tối đa quyền lợi cho người dùng.
				</div>
				<div class="users" style="border-bottom: 1px solid #76b852;">Trong
					quá trình sử dụng, nếu có bất cứ vấn đề gì mình sẽ xử lý, nếu không
					xử lý được mình sẽ hoàn tiền, nhưng xác suất xảy ra lỗi rất thấp.</div>
			</div>
			<div
				style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24); width: 100%; border-top: 10px solid #76b852; height: auto">
				<div class="contact">
					<a style="float: left; color: white;"
						href="https://www.facebook.com/profile.php?id=100014609199798"
						target="_blank">FACEBOOK ADMIN</a><a
						style="float: right; color: white;"
						href="https://www.facebook.com/enj0y2gether/" target="_blank">FACEBOOK
						FANPAGE </a>
				</div>
			</div>
		</div>
	</div>
	<div class="credit">2020 © Designed by enjoy2gether</div>
	<script type="text/javascript">
		document.getElementsByName("homePage")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("homePage")[0].style.color = "white";
	</script>
</body>
</html>