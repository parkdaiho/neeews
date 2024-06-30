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
		<c:forEach items="${comments}" var="comment">
			<div class="my-comment-in-list" onclick="showMyComment('${comment.domain}', ${comment.parentId});">
				<div class="my-comments-contents">
					${comment.contents}
				</div>
				<div class="my-comments-figures">
					<div class="my-comments-figure">
						${comment.createdAt}
					</div>
					<div class="my-comments-figure">
						${comment.good}
					</div>
					<div class="my-comments-figure">
						${comment.bad}
					</div>
				</div>
			</div>
			</c:forEach>
	</div>
</div>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</div>
</body>
</html>
