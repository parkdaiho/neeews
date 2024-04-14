<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>ARTICLE</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>ARTICLES</h1>
		<p>FIND ARTICLES AND READ IT!</p>
	</section>
	<section class="article-search-area">
		<jsp:include page="article-search-area.jsp"></jsp:include>
	</section>
	<section class="article-area">
		<div class="article-info">
			<div class="article-title">
				${title}
			</div>
			<div class="article-pubDate">
				${pubDate}
			</div>
			<div class="article-views">
				${views}
			</div>
		</div>
		<div class="article-contents">
			<div class="article-contents-images">
				이미지가 없습니다.
			</div>
			<div class="article-contents-textarea">
				${text}
			</div>
		</div>
	</section>
	<section class="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>