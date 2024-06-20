<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE</title>
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
		<h1>NOTICE</h1>
		<p>Read notice and give me your opinion!</p>
	</section>
	<section class="search-area">
		<jsp:include page="notice-search-area.jsp"></jsp:include>
	</section>
	<section class="notice-area">
		<div class="notice-info">
			<div class="notice-title">
				TITLE : ${title}
			</div>
			<div class="notice-created-at">
				CREATED-AT : ${createdAt}
			</div>
			<div class="notice-modified-at">
				MODIFIED-AT : ${modifiedAt}
			</div>
		</div>
		<div class="notice-contents">
			<div class="notice-contents-images">
				<c:forEach var="fileName" items="${savedFileNames}">
					<c:url var="src" value="/image">
						<c:param name="id" value="${id}"></c:param>
						<c:param name="domain" value="notice"></c:param>
						<c:param name="savedFileName" value="${fileName}"></c:param>
					</c:url>
					<img src="${src}">
				</c:forEach>
			</div>
			<div class="notice-contents-textarea">
				${text}
			</div>
		</div>
		<div class="notice-btn-area">
			<button onclick="getModifyNotice(${id});">MODIFY</button>
			<button onclick="deleteNotice(${id});">DELETE</button>
			<button onclick="location.replace('/notice-list');">LIST</button>
		</div>
	</section>
	<section class="comments-area" id="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/notice.js"></script>
<script src="/js/comments-area.js"></script>
<script src="/js/poll.js"></script>

</body>
</html>
