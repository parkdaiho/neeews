<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>CLIPPINGS</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>CLIPPINGS</h1>
		<p>You can check out the clipped articles later.</p>
	</section>
	<div class="clippings-area">
		<div class="clippings-info-area">
			<div class="clippings-total-articles">
				TOTAL <b>${totalElements}</b> CLIPPINGS
			</div>
		</div>
		<div class="clippings-list-area">
			<div class="clipping-list-top">
				<div class="clipping-checkbox"></div>
				<div class="clipping-top-title">
					TITLE
				</div>
				<div class="clipping-pubdate">
					PUB-DATE
				</div>
			</div>
			<c:forEach var="clipping" items="${articles}">
				<div class="clipping-in-list">
						<div class="clipping-checkbox">
							<input type="checkbox" class="clippings-checkbox" id="clipping-${clipping.id}">
						</div>
						<div class="clipping-title" onclick="showClipping(${clipping.id});">
							${clipping.title}
						</div>
						<div class="clipping-pubdate" onclick="showClipping(${clipping.id});">
							${clipping.pubDate}
						</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<c:if test="${totalElements != 0}">
		<section class="clippings-btn-area">
			<button onclick="cancelClipping();">CANCEL CLIPPING</button>
		</section>
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/clippings.js"></script>

</body>
</html>
