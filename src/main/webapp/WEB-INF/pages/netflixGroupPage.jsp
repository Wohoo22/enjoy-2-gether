<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/groupPage.css">
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
			<h1>${ groupName}(${service })</h1>
		</div>
		<div class="warn"></div>
		<div class="form">
			<h1>Nhóm đã có ${quantity }/5 thành viên.</h1>

			<c:if test="${quantity < 5 }">
				<h1>Tình trạng: Đang chờ.</h1>
			</c:if>

			<c:if test="${quantity >= 5 }">
				<h1>Tình trạng: Đã đầy</h1>
			</c:if>

			<h2>⚠️Lưu ý: Hãy nạp tiền để được cung cấp tài khoản sớm nhất.</h2>
			<br>

			<c:if test="${code == 100 }">
				<form class="login-form"
					action="${pageContext.request.contextPath}/inGroup/${idgroup}"
					method="GET">
					<input type="hidden" name="postNum" value="10">
					<button type="submit">Bạn đã là thành viên của nhóm, vào
						nhóm</button>
				</form>
			</c:if>

			<c:if test="${code == 200 }">
				<button style="background-color: #c7ebcf; color: gray">Bạn
					đã là thành viên của nhóm khác, không thể tham gia</button>
			</c:if>

			<c:if test="${code == 300 }">
				<s:url var="formUrl" value="/groupInfo/netflix/${idGroup }" />
				<sf:form modelAttribute="formGroupUser" action="${formUrl }"
					method="POST" class="login-form">
					<div>
						<sf:input path="iduser" readonly="true" />
					</div>
					<div>
						<sf:input path="idgroup" readonly="true" />
					</div>
					<div class="submit">
						<button type="submit" name="submit">THAM GIA NHÓM</button>
					</div>
					<script type="text/javascript">
						document.getElementsByTagName('input')[0].value = "${idUser}";
						document.getElementsByTagName('input')[1].value = "${idGroup}";
						document.getElementById("iduser").type = "hidden";
						document.getElementById("idgroup").type = "hidden";
					</script>
				</sf:form>
			</c:if>

			<c:if test="${code == 400 }">
				<button style="background-color: #c7ebcf; color: gray">Nhóm
					đã đầy người</button>
			</c:if>
		</div>
	</div>

	<script type="text/javascript">
		document.getElementsByName("groupList")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("groupList")[0].style.color = "white";
	</script>
</body>
</html>