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
	<div>
		<h1>Form cộng tiền vào tài khoản cho user</h1>
		<form
			action="${pageContext.request.contextPath}/admin/receiveAddBalanceRequest"
			method="GET">
			<div>
				Username: <input type="text" name="username">
			</div>
			<div>
				Add balance: <input type="text" name="money">
			</div>
			<button type="submit">Submit</button>
		</form>

		<div>
			<a>Vừa nạp cho user: ${username }, balance cũ: ${oldBalance },
				balance sau nạp: ${newBalance }</a>
		</div>
	</div>
</body>
</html>