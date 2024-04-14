<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<p>We provide articles that match the search term.</p>
	</section>
	<section class="article-search-area">
		<jsp:include page="article-search-area.jsp"></jsp:include>
	</section>
	<div class="searched-articles-info">
		<div class="searched-articles-total-users">
			TOTAL ${totalElements} ARTICLES
		</div>
	</div>
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
			<c:forEach var="item" items="${items}" varStatus="index">
				<div class="searched-article" onclick="showArticle(${index});">
					<input type="hidden" value="${item.originallink}" id="item_originalLink_${index}">
					<input type="hidden" value="${item.link}" id="item_link_${index}">
					<div class="article-title" id="item_title_${index}">
						${item.title}
					</div>
					<div class="article-description" id="item_description_${index}">
							${item.description}
					</div>
					<div class="article-pubdate" id="item_pubDate_${index}">
							${item.pubDate}
					</div>
				</div>
			</c:forEach>
			<c:if test="${totalElements != 0}">
				<jsp:include page="board-pagination.jsp"></jsp:include>
			</c:if>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/articles.js"></script>

</body>
</html>
