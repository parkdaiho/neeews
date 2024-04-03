<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>SEARCHED-ARTICLES</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>ARTICLES</h1>
		<p>We provide 100 articles that match the search term.</p>
	</section>
	<section class="article-search-area">
		<jsp:include page="article-search-area.jsp"></jsp:include>
	</section>
	<div class="searched-articles-area">
		<div class="article-in-searched-articles">
			<div class="searched-article-top">
				<div class="article-title">
					TITLE
				</div>
				<div class="article-description">
					DESCRIPTION
				</div>
				<div class="article-pubdate">
					PUBDATE
				</div>
			</div>
			<div class="searched-article">
				<div class="article-title">
					TITLE-1
				</div>
				<div class="article-description">
					DESCRIPTION-1
				</div>
				<div class="article-pubdate">
					2024.03.27
				</div>
			</div>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
