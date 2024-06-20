<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE-LIST</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>NOTICE</h1>
		<p>Check out notice for using our site!</p>
	</section>
	<section class="search-area">
		<jsp:include page="notice-search-area.jsp"></jsp:include>
	</section>
	<section class="posts-area">
		<div class="posts-info-area">
			<span>TOTAL <b>${totalElements}</b> NOTICE</span>
			<select onchange="getNoticeByOrder(this.value);">
				<c:choose>
					<c:when test="${order == 'views'}">
						<option value="latest">LATEST</option>
						<option value="views" selected>MOST-VIEWS</option>
						<option value="comments">MOST-COMMENTS</option>
					</c:when>
					<c:when test="${order == 'comments'}">
						<option value="latest">LATEST</option>
						<option value="views">MOST-VIEWS</option>
						<option value="comments" selected>MOST-COMMENTS</option>
					</c:when>
					<c:otherwise>
						<option value="latest" selected>LATEST</option>
						<option value="views">MOST-VIEWS</option>
						<option value="comments">MOST-COMMENTS</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div class="post-list">
			<div class="post-in-posts-top">
				<div class="post-id">NUM</div>
				<div class="post-top-title">TITLE</div>
				<div class="post-writer"></div>
				<div class="post-created-at">CREATED-AT</div>
			</div>
			<c:forEach var="notice" items="${fixedNoticeList}">
				<div class="notice-fixed-in-list" onclick="getNoticeView(${notice.id});">
					<div class="post-id"></div>
					<div class="post-title">${notice.title}</div>
					<div class="post-writer"></div>
					<div class="post-created-at">${notice.createdAt}</div>
				</div>
			</c:forEach>
			<c:forEach var="notice" items="${noticeList}">
				<div class="post-in-posts" onclick="getNoticeView(${notice.id});">
					<div class="post-id">${notice.id}</div>
					<div class="post-title">${notice.title}</div>
					<div class="post-writer"></div>
					<div class="post-created-at">${notice.createdAt}</div>
				</div>
			</c:forEach>
		</div>
	</section>
	<c:if test="${isManager}">
		<div class="posts-btn">
			<button onclick="location.replace('new-notice');">NEW-NOTICE</button>
		</div>
	</c:if>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</main>
<footer>
	FOOTER
</footer>

<script src="/js/notice-list.js"></script>

</body>
</html>
