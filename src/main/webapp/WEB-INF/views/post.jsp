<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>POST</title>
	<script>
		const id = ${id};
		const domain = "${domain}";
	</script>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>COMMUNITY</h1>
		<p>Read the post and share your opinions with people in the comments!</p>
	</section>
	<section class="search-area">
		<jsp:include page="post-search-area.jsp"></jsp:include>
	</section>
	<section class="post-area">
		<div class="post-info">
			<div class="post-title">
				${title}
			</div>
			<div class="post-figures">
				<div class="post-figure">
					<span>작성자</span>${writer}
				</div>
				<div class="post-figure">
					<span>작성일</span>${createdAt}
				</div>
				<div class="post-figure">
					<span>수정일</span>${modifiedAt}
				</div>
				<div class="post-figure">
					<span>조회수</span>${views}
				</div>
			</div>
		</div>
		<div class="post-contents">
			<div class="post-contents-images">
				<c:forEach var="filename" items="${savedFileNames}">
					<c:url var="src" value="/image">
						<c:param name="id" value="${id}"></c:param>
						<c:param name="domain" value="post"></c:param>
						<c:param name="savedFileName" value="${filename}"></c:param>
					</c:url>
					<img src="${src}">
				</c:forEach>
			</div>
			<div class="post-contents-textarea">
				${text}
			</div>
		</div>
		<div class="poll-area">
			<jsp:include page="poll-area.jsp"></jsp:include>
		</div>
	</section>
	<section class="post-btn-area">
		<button onclick="getModifyPost(${id});">MODIFY</button>
		<button onclick="deletePost(${id});">DELETE</button>
		<button onclick="location.replace('/posts');">LIST</button>
	</section>
	<section class="comments-area" id="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/post.js"></script>
<script src="/js/comments-area.js"></script>

</body>
</html>
