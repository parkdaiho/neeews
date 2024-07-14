<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NEW-POST</title>
	<script>document.execCommand('defaultParagraphSeparator', false, 'br');</script>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>POST</h1>
		<p>Add a post and share your opinion with people!</p>
	</section>
	<section class="new-post-area">
		<div class="new-post-title">
			<span>TITLE</span>
			<input type="text" id="new-post-title" value="${title}">
		</div>
		<div class="new-post-textarea">
			<div class="new-post-text" id="new-post-text" contenteditable="true">
				${text}
			</div>
		</div>

		<jsp:include page="upload-image-area.jsp"></jsp:include>

	</section>
	<section class="new-post-btn">
		<c:choose>
			<c:when test="${id != null}">
				<button onclick="modifyPost(${id})">MODIFY</button>
			</c:when>
			<c:otherwise>
				<button onclick="writePost();">ADD</button>
				<c:if test="${articleId != null}">
					<input type="hidden" value="${articleId}" id="new-post-article-id">
				</c:if>
			</c:otherwise>
		</c:choose>
		<button onclick="location.replace('/posts');">LIST</button>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/new-post.js"></script>

</body>
</html>
