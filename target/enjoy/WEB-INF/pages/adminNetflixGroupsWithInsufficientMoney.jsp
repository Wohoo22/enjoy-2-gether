<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.top a {
	padding: 10px;
	border: 1px solid gray;
}

.top {
	padding: 10px;
}
</style>
</head>
<body>
	<jsp:include page="adminMenu.jsp"></jsp:include>

	<div style="text-align: center;">
		<h1>Group NETFLIX đã đủ người nhưng chưa đủ tiền</h1>
	</div>

	<div class="top">
		Sort: <a
			href=" ${pageContext.request.contextPath}/admin/groups/netflix/insufficientBalance?sort=0">
			Group chưa có acc</a>|<a
			href=" ${pageContext.request.contextPath}/admin/groups/netflix/insufficientBalance?sort=1">
			Group đã có acc mà hết hạn</a>|<a
			href=" ${pageContext.request.contextPath}/admin/groups/netflix/insufficientBalance?sort=2">
			Group đã có accc, đang hoạt động (mới add/gia hạn)</a>
	</div>

	<c:forEach items="${groupList}" var="group">
		<div style="float: left; border: 1px solid gray;">
			<div>
				<h1>${ group.groupname}</h1>
			</div>
			<c:forEach items="${usersOfGroupsList}" var="user">
				<c:if test="${user.idgroup == group.idgroup }">
					<div>
						<a>${ user.username}_${user.balance }_joinGroup:${user.dateJoinGroup }</a>
					</div>
				</c:if>
			</c:forEach>
			<div>
				<a
					href="${pageContext.request.contextPath}/admin/inGroup/${group.idgroup}?postNum=10">Vao
					group</a>
			</div>

			<div style="border: 1px solid gray;">
				<div>
					<a style="color: green;"> ${group.account.email }</a>
				</div>
				<div>
					<a style="color: green;">${group.account.password }</a>
				</div>
				<div>
					<a style="color: green;">${group.account.expiredDate }</a>
				</div>

			</div>
		</div>
	</c:forEach>
</body>
</html>