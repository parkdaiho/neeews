<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>ARTICLE</title>
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
		<h1>ARTICLES</h1>
		<p>FIND ARTICLES AND READ IT!</p>
	</section>
	<section class="search-area">
		<jsp:include page="article-search-area.jsp"></jsp:include>
	</section>
	<section class="article-area">
		<div class="article-info">
			<div class="article-title">
				${title}
			</div>
			<div class="article-figures">
				<div class="article-figure">
					<span>작성일</span>${pubDate}
				</div>
				<div class="article-figure">
					<span>조회수</span>${views}
				</div>
			</div>
		</div>
		<div class="article-contents">
			<c:choose>
				<c:when test="${isProvided}">
					<div class="article-contents-images">
						<c:if test="${imgSrc != null}">
							<img src="${imgSrc}">
						</c:if>
					</div>
					<div class="article-contents-textarea">
							${text}
					</div>
					<div class="article-originalLink">
						<a href="${originalLink}">VIEW THE ORIGINAL ARTICLE</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="article-contents-textarea">
						저희 사이트에서 제공되지 않는 기사입니다.
					</div>
					<div class="article-originalLink">
						<a href="${originalLink}">VIEW THE ORIGINAL ARTICLE</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="poll-area">
			<jsp:include page="poll-area.jsp"></jsp:include>
		</div>
	</section>
	<section class="article-btn-area">
		<c:choose>
			<c:when test="${!isClipped}">
				<button onclick="cancelClipping();">CANCEL CLIPPING</button>
			</c:when>
			<c:otherwise>
				<button onclick="clippingArticle();">CLIPPING</button>
			</c:otherwise>
		</c:choose>
	</section>
	<section class="comments-area" id="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/article.js"></script>
<script src="/js/comments-area.js"></script>

</body>
</html>