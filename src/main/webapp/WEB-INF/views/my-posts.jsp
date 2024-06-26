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
		<div class="my-post-top">
			<div class="my-post-id">
				NUM
			</div>
			<div class="my-post-top-title">
				TITLE
			</div>
			<div class="my-post-created-at">
				CREATED-AT
			</div>
			<div class="my-post-views">
				VIEWS
			</div>
		</div>
		<div class="post-in-my-posts">
			<div class="my-post-id">
				...
			</div>
			<div class="my-post-title">
				...
			</div>
			<div class="my-post-created-at">
				...
			</div>
			<div class="my-post-views">
				...
			</div>
		</div>
	</div>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</div>
</body>
</html>
