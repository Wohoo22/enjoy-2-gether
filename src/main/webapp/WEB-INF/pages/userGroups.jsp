<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/groupList.css">
<link rel="icon"
	href="https://www.clipartmax.com/png/middle/21-210980_green-letter-e-clip-art-green-letter-e-clipart.png">
</head>
<body>
	<jsp:include page="topNav.jsp"></jsp:include>

	<br>
	<div class="container">
		<div class="label">
			<h1>Nhóm bạn đã tham gia</h1>
		</div>
		<br>
	</div>
	<br>
	<div class="groupList">
		<c:forEach items="${groupList}" var="group">
			<div class="group"
				style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24); width: 100%">
				<div class="name">${group.groupname}(${group.service})</div>
				<div class="users">${group.memberQuantity }/5</div>
				<form
					action="${pageContext.request.contextPath}/inGroup/${group.idgroup}"
					method="GET">
					<input type="hidden" name="postNum" value="5">
					<button class="button" type="submit">VÀO</button>
				</form>
			</div>
		</c:forEach>
	</div>
	<script type="text/javascript">
		document.getElementsByName("groupList")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("groupList")[0].style.color = "white";
	</script>
</body>
</html>