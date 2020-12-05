<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Enjoy2gether</title>
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
			<h1>Nhóm mua chung tài khoản NETFLIX.</h1>
		</div>
		<br>
		<div class="features">
			<c:if test="${groupCheck == 0 }">
				<a href="${pageContext.request.contextPath}/groups/netflix/create">Tạo
					nhóm</a>
			</c:if>

			<c:if test="${groupCheck == -1 }">
				<a
					href="${pageContext.request.contextPath}/groupInfo/netflix/${idgroupOfUser}">Nhóm
					của bạn</a>
			</c:if>

			<sort>
			<div class="dropdown">
				<button class="dropbtn">Sắp xếp</button>
				<div class="dropdown-content">
					<a
						href="${pageContext.request.contextPath}/groups/netflix?loadNum=10&sort=1">Số
						người tăng dần</a> <a
						href="${pageContext.request.contextPath}/groups/netflix?loadNum=10&sort=2">Số
						người giảm dần</a>

				</div>
			</div>
			</sort>
		</div>
		<br>
		<div class="groupList">
			<c:forEach items="${groupList}" var="group">
				<div class="group"
					style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
					<div class="name">${group.groupname}</div>
					<div class="users">${group.memberQuantity }/5</div>
					<form
						action="${pageContext.request.contextPath}/groupInfo/netflix/${group.idgroup}">
						<button class="button" type="submit">VÀO</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="loadMore">
		<a
			href="${pageContext.request.contextPath}/groups/netflix?loadNum=${loadNum }&sort=${sort}">Tải
			thêm nhóm</a>
	</div>

	<div class="credit">2020 © Designed by enjoy2gether</div>
	<script type="text/javascript">
		document.getElementsByName("groupList")[0].style.backgroundColor = "#4caf50";
		document.getElementsByName("groupList")[0].style.color = "white";
	</script>
</body>
</html>