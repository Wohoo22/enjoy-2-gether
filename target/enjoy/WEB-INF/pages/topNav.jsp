<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div class="topnav"
		style="box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);">
		<c:if test="${username != null}">
			<a name="userInfo" href="${pageContext.request.contextPath}/userInfo">Xin
				chào, ${username }</a>
		</c:if>
		<c:if test="${username == null}">
			<a name="login" href="${pageContext.request.contextPath}/login">Đăng
				nhập</a>
			<a name="register" href="${pageContext.request.contextPath}/register">Đăng
				kí</a>
		</c:if>
		<div class="group-menu">
			<a name="groupList">Nhóm ►</a>
			<div class="group-menu-drop">
				<a
					href="${pageContext.request.contextPath}/groups/netflix?loadNum=10&sort=0">Netflix</a>
				<a href="${pageContext.request.contextPath}/userGroups">Nhóm của
					bạn</a>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/info" name="infoPage">Thông
			tin</a> <a href="${pageContext.request.contextPath}/home" name="homePage">Giới
			thiệu</a>
		<logo>ENJOY2GETHER</logo>
	</div>
</body>
</html>