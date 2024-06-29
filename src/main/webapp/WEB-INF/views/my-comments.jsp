<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MY-COMMENTS</title>
</head>
<body>
<div class="my-comments-area">
	<div class="my-comments-info">
		<div class="my-comments-total-posts">
			TOTAL <b>${totalElements}</b> COMMENTS
		</div>
	</div>
	<div class="my-comments-list">
		<div class="my-comments-list-top">
			<div class="my-comments-post-id">
				POST-NUM
			</div>
			<div class="my-comments-top-contents">
				CONTENTS
			</div>
			<div class="my-comments-created-at">
				CREATED-AT
			</div>
			<div class="my-comments-figure">
				LIKE
			</div>
			<div class="my-comments-figure">
				DISLIKE
			</div>
		</div>
		<div class="my-comment-in-list">
			<div class="my-comments-post-id">
				...
			</div>
			<div class="my-comments-contents">
				...
			</div>
			<div class="my-comments-created-at">
				...
			</div>
			<div class="my-comments-figure">
				...
			</div>
			<div class="my-comments-figure">
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
