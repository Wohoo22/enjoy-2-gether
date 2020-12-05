<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="adminMenu.jsp"></jsp:include>
	<div style="text-align: center;">
		<h1>Group NETFLIX đã đủ người và đủ tiền</h1>
	</div>
	<div>
		<s:url var="formUrl" value="/admin/addNewAccount/netflix" />
		<sf:form modelAttribute="accountInfoForm" action="${formUrl }"
			method="POST">
			<fieldset>
				<div>
					<div>
						<h1>Form add account Netflix mới cho group</h1>
					</div>
					ID group:
					<sf:input path="idgroup" />
					EMAIL:
					<sf:input path="email" />
					PASSWORD:
					<sf:input path="password" />
				</div>

				<div>
					<div>
						<h1>Date dang ki</h1>
					</div>
					Ngay:
					<sf:input path="signedDay" />
					Thang
					<sf:input path="signedMonth" />
					Nam
					<sf:input path="signedYear" />
				</div>

				<div>
					<div>
						<h1>Date het han</h1>
					</div>
					Ngay:
					<sf:input path="expiredDay" />
					Thang
					<sf:input path="expiredMonth" />
					Nam
					<sf:input path="expiredYear" />
				</div>

				<div>
					<button type="submit">Submit</button>
				</div>
			</fieldset>
		</sf:form>
	</div>

	<div>
		<s:url var="updateUrl" value="/admin/updateAccountExpiredDate/netflix" />
		<sf:form modelAttribute="updateExpiredDateForm" action="${updateUrl }"
			method="POST">
			<fieldset>
				<h1>Form update expired date cho account Netflix cũ</h1>
				Email:
				<sf:input path="email" />
				Ngay:
				<sf:input path="day" />
				Thang
				<sf:input path="month" />
				Nam
				<sf:input path="year" />
				<button type="submit">submit</button>
			</fieldset>
		</sf:form>
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
				<a style="color: red;">${group.account.email }</a> <br> <a
					style="color: red;"> ${group.account.password }</a> <br> <a
					style="color: red;"> ${group.account.expiredDate }</a>
			</div>
		</div>
	</c:forEach>

</body>
</html>