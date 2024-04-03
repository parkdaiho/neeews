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
		<div class="post-list">
			<div class="post-in-posts-top">
				<div class="post-num">NUM</div>
				<div class="post-title">TITLE</div>
				<div class="post-writer">WRITER</div>
				<div class="post-created-at">CREATED-AT</div>
			</div>
			<div class="post-in-posts">
				<div class="post-num">1</div>
				<div class="post-title">TITLE-1</div>
				<div class="post-writer">aaa</div>
				<div class="post-created-at">2024.03.27</div>
			</div>
			...
		</div>
		<jsp:include page="board-pagination.jsp"></jsp:include>
		<div class="posts-btn">
			<button>NEW-POST</button>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
