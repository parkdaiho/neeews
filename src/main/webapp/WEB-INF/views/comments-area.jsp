<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="comments-info">
	<span>총 ${comments.size} 개 </span>
	<c:if test="${sort eq 'date'}">
		<a href="/article-comments?sort=date" class="on">최신순</a>
		<a href="/article-comments?sort=good">인기순</a>
	</c:if>
	<c:if test="${sort eq 'good'}">
		<a href="/article-comments?sort=date">최신순</a>
		<a href="/article-comments?sort=good" class="on">인기순</a>
	</c:if>
</div>
<div id="comments-view">
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
<div id="comments-page">
	<ul>
		<li><a href="<c:url value="/article-comments?page=1&sort=${sort}"/>"><<</a></li>
		<li><a href="<c:url value="/article-comments?page=${previousPage}&sort=${sort}"/>"><</a></li>
		<c:forEach var="i" begin="${firstNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
			<li><a href="<c:url value="/article-comments?page=${i}&sort=${sort}"/>">${i}</a></li>
		</c:forEach>
		<li><a href="<c:url value="/article-comments?page=${nextPage}&sort=${sort}"/>">></a></li>
		<li><a href="<c:url value="/article-comments?page=${totalPages}&sort=${sort}"/>">>></a></li>
	</ul>
</div>
</body>
</html>
