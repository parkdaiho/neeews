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
	<div class="my-posts-info">
		<div class="my-posts-total-posts">
			TOTAL <b>${totalElements}</b> POSTS
		</div>
	</div>
	<div class="my-posts-list">
		<c:forEach items="${posts}" var="post">
			<div class="post-in-my-posts" onclick="showMyPost(${post.id});">
				<div class="my-post-title">
					${post.title}
				</div>
				<div class="my-post-figures">
					<div class="my-post-figure">
						${post.createdAt}
					</div>
					<div class="my-post-figure">
						${post.views}
					</div>
					<div class="my-post-figure">
						${post.commentsSize}
					</div>
					<div class="my-post-figure">
						${post.good}
					</div>
					<div class="my-post-figure">
						${post.bad}
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
