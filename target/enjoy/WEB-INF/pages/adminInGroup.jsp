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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/inGroup.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/topnav.css">
</head>
<body>
	<jsp:include page="adminMenu.jsp"></jsp:include>

	<button onclick="topFunction()" id="myBtn" title="Go to top">▲</button>

	<div class="label">
		<h1>GROUP ${id } (${service })</h1>
	</div>
	<div class="pinnedPost">
		Các bạn có thể tham gia group chat nếu có thắc mắc gì tại đây, admin
		sẽ luôn có mặt để giải đáp cho các bạn: <br> <a>GROUP
			FACEBOOK</a> <br> <a>GROUP CHAT DISCORD</a>
	</div>
	<br>
	<div class="pinnedPost">
		<warn> ⚠️ Các bạn nhớ nạp tiền nhanh để mình và những thành
		viên trong nhóm có tài khoản xem Netflix nhé</warn>
		<br>
		<warn>⚠️ Nếu có 1 thành viên mãi không nạp tiền trong khi những
		người còn lại đã nạp rùi thì admin xin phép kick bạn đó ra nhé, tiết
		kiệm thời gian cho những người đã nạp tiền</warn>
	</div>

	<br>
	<div class="forum">

		<div class="acc">
			<c:if test="${haveAccount == 0 }">
				<a> TÀI KHOẢN: ${accountEmail}</a>
				<br>
				<a> MẬT KHẨU: ${accountPassword } </a>
				<br>
				<button>Copy email đăng nhập</button>
				<button>Copy mật khẩu</button>
				<a>Ngày hết hạn: ${expiredDate }</a>
			</c:if>

			<c:if test="${haveAccount == 1}">
				<a>Nhóm chưa có tài khoản </a>
			</c:if>

			<c:if test="${haveAccount == 2}">
				<a>Tài khoản đã hết hạn, các thành viên vui lòng nạp tiền để sử
					dụng tiếp </a>
			</c:if>

			<div class="memberList">
				<g>MEMBERS</g>
				<br>
				<c:forEach items="${userList }" var="user">
					<a>${user.username }</a>
					<br>
				</c:forEach>
			</div>
			<br>
			<div class="memberList">
				<g>MEMBER CHƯA ĐỦ TIỀN</g>
				<br>
				<c:forEach items="${userList }" var="user">
					<c:if test="${user.balance < servicePrice}">
						<a>${user.username }</a>
						<br>
					</c:if>
				</c:forEach>
			</div>
		</div>

		<div class="form">
			<div class="createPost">
				<s:url var="addPostUrl" value="/admin/inGroup/${id }/submitPost" />
				<sf:form class="createPostForm" modelAttribute="postForm"
					action="${addPostUrl }" method="POST">
					<sf:textarea path="postDetail" />
					<button type="submit" name="buttonPost">Đăng post</button>
				</sf:form>
			</div>
			<br>


			<c:forEach items="${postList}" var="post">
				<div class="post">
					<div class="postTop">
						<username>${ post.username}:</username>
						<c:if test="${username == post.username }">
							<s:url var="deletePostUrl"
								value="/admin/ingroup/${id}/${post.idpost}/deletePost" />
							<sf:form action="${ deletePostUrl}" method="POST">
								<button type="submit"
									onclick="return confirm('Bạn có chắc chắn muốn xóa ?')">Xóa</button>
							</sf:form>
						</c:if>
					</div>
					<div class="postDetail">
						<a> ${ post.postDetail} </a>
					</div>

					<c:forEach items="${commentList }" var="comment">
						<c:if test="${comment.idpost == post.idpost}">

							<div class="commentDetail">
								<div class="commentText">
									<usernameComment>${comment.username}:</usernameComment>
									<a>${comment.commentDetail }</a>
								</div>

								<c:if test="${username == comment.username }">
									<s:url var="deleteCommentUrl"
										value="/admin/ingroup/${id}/${comment.idcomment}/deleteComment" />
									<sf:form action="${ deleteCommentUrl}" method="POST">
										<button
											style="float: right; background-color: #dee3df, border:0; font-size: 12px; font-family: 'Roboto', sans-serif; color: black;"
											onclick="return confirm('Bạn có chắc chắn muốn xóa ?')">Xóa</button>
									</sf:form>
								</c:if>
							</div>
						</c:if>
					</c:forEach>



					<div class="commentDetail">
						<s:url var="addCommentUrl"
							value="/admin/inGroup/${id}/${post.idpost}/submitComment" />
						<sf:form class="createCommentForm" modelAttribute="commentForm"
							action="${addCommentUrl }" method="POST">
							<sf:textarea path="commentDetail" />
							<button name="buttonComment">Bình luận</button>
						</sf:form>
					</div>
				</div>
				<br>
			</c:forEach>


			<br>

			<div class="loadMorePost">
				<a
					href="${pageContext.request.contextPath}/admin/inGroup/${id}?postNum=${postNum}">Tải
					thêm post</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		//Get the button
		var mybutton = document.getElementById("myBtn");

		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {
			scrollFunction()
		};

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				mybutton.style.display = "block";
			} else {
				mybutton.style.display = "none";
			}
		}

		// When the user clicks on the button, scroll to the top of the document
		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
</body>
</html>