<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>ARTICLES</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>ARTICLES</h1>
		<p>Read the articles people interested in!</p>
	</section>
	<section class="article-search-area">
		<jsp:include page="article-search-area.jsp"></jsp:include>
	</section>
	<section class="articles-area">
		<div class="article-list-area">
			<h2>MOST POPULAR ARTICLES</h2>
			<div class="article-list">
				<div class="article-in-list">
					<div class="article-rank">
						1
					</div>
					<div class="article-title">
						TITLE
					</div>
					<div class="article-description">
						DESCRIPTION
					</div>
					<div class="article-pubdate">
						2024.03.27
					</div>
				</div>
				...
			</div>
		</div>
		<div class="article-list-area">
			<h2>MOST VIEWED ARTICLES</h2>
			<div class="article-list">
				<div class="article-in-list">
					<div class="article-rank">
						1
					</div>
					<div class="article-title">
						TITLE
					</div>
					<div class="article-description">
						DESCRIPTION
					</div>
					<div class="article-pubdate">
						2024.03.27
					</div>
				</div>
				...
			</div>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
