<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/groupList.css">
</head>
<body>
	<jsp:include page="adminMenu.jsp"></jsp:include>

	<div class="container">
		<div class="label">
			<h1>Nhóm mua chung tài khoản NETFLIX.</h1>
		</div>
		<br>
		<div class="features">
			<sort>
			<div class="dropdown">
				<button class="dropbtn">Sắp xếp</button>
				<div class="dropdown-content">
					<a
						href="${pageContext.request.contextPath}/admin/groups/netflix?loadNum=10&sort=1">Số
						người tăng dần</a> <a
						href="${pageContext.request.contextPath}/admin/groups/netflix?loadNum=10&sort=2">Số
						người giảm dần</a>

				</div>
			</div>
			</sort>
		</div>
		<br>
		<div class="groupList">
			<c:forEach items="${groupList}" var="group">
				<div class="group" name="${group.memberQuantity }-mem"
					style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
					<div class="name">${group.groupname}</div>
					<div class="users">${group.memberQuantity }/5</div>
					<form
						action="${pageContext.request.contextPath}/admin/inGroup/${group.idgroup}"
						method="GET">
						<input type="hidden" name="postNum" value="10">
						<button class="button" type="submit">VÀO</button>
					</form>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="loadMore">
		<a
			href="${pageContext.request.contextPath}/admin/groups/netflix?loadNum=${loadNum }&sort=${sort}">Tải
			thêm nhóm</a>
	</div>
</body>
</html>