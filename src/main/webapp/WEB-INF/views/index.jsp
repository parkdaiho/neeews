<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="index-info-area">
		<h1>Find articles and share your opinion!</h1>
		<form action="/articles" method="post">
			<div class="index-search-box">
				<select name="domain" onchange="changeSearchDomain(this.value);">
					<option value="article" selected>ARTICLE</option>
					<option value="post">POST</option>
				</select>
				<select name="sort" id="sort">
					<option value="sim">MOST-SIMILAR</option>
					<option value="date">LATEST</option>
				</select>
				<input type="text" name="query" placeholder="Search ...">
				<button type="submit">SEARCH</button>
			</div>
		</form>
	</section>
	<section class="index-middle-area">
		<div class="index-middle-part" id="index-articles-part">
			<div class="index-middle-info">
				<h2>ARTICLES</h2>
				<select onchange="getIndexArticles(this.value);">
					<option value="views">MOST-VIEWED</option>
					<option value="popularity">MOST-POPULAR</option>
				</select>
			</div>
			<div class="index-middle-contents">
				<ul>
					<c:forEach var="article" items="${articles}">
						<li><a href="${article.link}">${article.title}</a><span>${article.figure}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="index-middle-part" id="index-posts-part">
			<div class="index-middle-info">
				<h2>POSTS</h2>
				<select onchange="getIndexPosts(this.value);">
					<option value="views">MOST-VIEWED</option>
					<option value="popularity">MOST-POPULAR</option>
				</select>
			</div>
			<div class="index-middle-contents">
				<ul>
					<c:forEach var="post" items="${posts}">
						<li><a href="${post.link}">${post.title}</a><span>${post.figure}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/index.js"></script>

</body>
</html>
