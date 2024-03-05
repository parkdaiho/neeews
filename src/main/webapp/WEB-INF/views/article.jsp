<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div>
	<input type="hidden" id="article-id" value="${article.id}">
	<div>
		${article.title}
	</div>
	<div>
		<c:choose>
			<c:when test="${article.isProvided}">
				${article.texts}
			</c:when>
			<c:otherwise>
				제공되지 않는 기사입니다.
				<a href="${article.originalLink}">원문으로 이동</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
    <textarea id="comment-create-contents">

    </textarea>
		<button id="comment-create-button">댓글작성</button>
	</div>
	<div id="comments-area">
		<c:set var="comments" value="${article.comments}"></c:set>
		<c:forEach var="comment" items="${comments}">
			<div id="comment${id}">
				<div id="comment${id}writer">
						${comment.writer}
				</div>
				<div id="comment${id}contents">
						${comment.contents}
				</div>
				<div id="comment${id}createdDate">
						${comment.createdDate}
				</div>
			</div>
<%--			<c:forEach var="reply" items="${comment.reply}">--%>
<%--				<div id="reply${id}">--%>
<%--					<div id="reply${id}writer">--%>
<%--							${reply.writer}--%>
<%--					</div>--%>
<%--					<div id="reply${id}contents">--%>
<%--							${reply.contents}--%>
<%--					</div>--%>
<%--					<div id="reply${id}createdDate">--%>
<%--							${reply.createdDate}--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</c:forEach>--%>
		</c:forEach>
	</div>
</div>
<script src="/js/articleView.js"></script>
</body>
</html>
