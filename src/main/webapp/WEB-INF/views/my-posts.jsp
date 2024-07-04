<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MY-POSTS</title>
</head>
<body>
<div class="my-posts-area">
	<div class="my-posts-info-area">
		<div class="my-posts-total-posts">
			TOTAL <b>${totalElements}</b> POSTS
		</div>
	</div>
	<div class="my-posts-list">
		<c:forEach var="post" items="${posts}">
			<div class="post-in-my-posts" onclick="showMyPost(${post.id});">
				<div class="my-post-title">
					${post.title}
				</div>
				<div class="my-post-figures">
					<div class="my-post-created-at">
						${post.createdAt}
					</div>
					<div class="my-post-figure">
						<b>조회수</b>&nbsp;&nbsp;${post.views}
					</div>
					<div class="my-post-figure">
						<b>댓글수</b>&nbsp;&nbsp;${post.commentsSize}
					</div>
					<div class="my-post-figure">
						<b>좋아요</b>&nbsp;&nbsp;${post.good}
					</div>
					<div class="my-post-figure">
						<b>싫어요</b>&nbsp;&nbsp;${post.bad}
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</div>
</body>
</html>
