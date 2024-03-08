<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="comments-info">
	<span>총 ${totalElements} 개의 댓글 </span>
	<c:if test="${sort eq 'date'}">
		<button id="sort-by-date" class="on" onclick="getPage(1, 'date')">최신순</button>
		<button id="sort-by-good" onclick="getPage(1, 'good')">인기순</button>
	</c:if>
	<c:if test="${sort eq 'good'}">
		<button id="sort-by-date" onclick="getPage(1, 'date')">최신순</button>
		<button id="sort-by-good" class="on" onclick="getPage(1, 'good')">인기순</button>
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
		<li><button id="load-first-page-of-comments" onclick="getPage(1, ${sort})"><<</button></li>
		<li><button id="load-previous-page-of-comments" onclick="getPage(${previousPage}, ${sort})"><</button></li>
		<c:forEach var="i" begin="${firstNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
			<li><button class="page-btn" onclick="getPage(${i}, ${sort})">${i}</button></li>
		</c:forEach>
		<li><button id="load-next-page-of-comments" onclick="getPage(${nextPage}, ${sort})">></button></li>
		<li><button id="load-last-page-of-comments" onclick="getPage(${totalPages}, ${sort})">>></button></li>
	</ul>
</div>
<script src="/js/article-comment.js"></script>
</body>
</html>
