<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				TITLE: ${title}
			</div>
			<div class="article-pubDate">
				PUBDATE: ${pubDate}
			</div>
			<div class="article-views">
				VIWES: ${views}
			</div>
		</div>
		<div class="article-contents">
			<div class="article-contents-images">
				<c:choose>
					<c:when test="${imgSrc != null}">
						<img src="${imgSrc}">
					</c:when>
					<c:otherwise>
						이미지가 없습니다.
					</c:otherwise>
				</c:choose>
			</div>
			<div class="article-contents-textarea">
				${text}
			</div>
			<div class="article-originalLink">
				<a href="${originalLink}">VIEW THE ORIGINAL ARTICLE</a>
				<br>
				<a href="${link}">VIEW THE ORIGINAL ARTICLE</a>
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