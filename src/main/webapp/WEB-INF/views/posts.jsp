<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>POSTS</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>COMMUNITY</h1>
		<p>Read posts you interested in!</p>
	</section>
	<section class="post-search-area">
		<jsp:include page="post-search-area.jsp"></jsp:include>
	</section>
	<div class="posts-area">
		<div class="posts-info-area">
			TOTAL ${totalElements} POSTS!
			<select onchange="getPostsByOrder(this.value);">
				<c:choose>
					<c:when test="${order == 'views'}">
						<option value="latest">LATEST</option>
						<option value="views" selected>MOST-VIEWED</option>
						<option value="popularity">MOST-POPULAR</option>
						<option value="comments">MOST-COMMENTS</option>
					</c:when>
					<c:when test="${order == 'popularity'}">
						<option value="latest">LATEST</option>
						<option value="views">MOST-VIEWED</option>
						<option value="popularity" selected>MOST-POPULAR</option>
						<option value="comments">MOST-COMMENTS</option>
					</c:when>
					<c:when test="${order == 'comments'}">
						<option value="latest">LATEST</option>
						<option value="views">MOST-VIEWED</option>
						<option value="popularity">MOST-POPULAR</option>
						<option value="comments" selected>MOST-COMMENTS</option>
					</c:when>
					<c:otherwise>
						<option value="latest" selected>LATEST</option>
						<option value="views">MOST-VIEWED</option>
						<option value="popularity">MOST-POPULAR</option>
						<option value="comments">MOST-COMMENTS</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div class="post-list">
			<div class="post-in-posts-top">
				<div class="post-num">NUM</div>
				<div class="post-title">TITLE</div>
				<div class="post-writer">WRITER</div>
				<div class="post-created-at">CREATED-AT</div>
			</div>
			<c:forEach var="post" items="${posts}">
				<div class="post-in-posts" onclick="getPostView(${post.id});">
					<div class="post-num">${post.id}</div>
					<div class="post-title">${post.title}</div>
					<div class="post-writer">${post.writer}</div>
					<div class="post-created-at">${post.createdAt}</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${totalElements != 0}">
			<jsp:include page="board-pagination.jsp"></jsp:include>
		</c:if>
		<div class="posts-btn">
			<button onclick="location.replace('/new-post');">NEW-POST</button>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/posts.js"></script>

</body>
</html>
