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
	<div class="new-post-area">
		<div class="new-post-title">
			TITLE: <input type="text" placeholder="TITLE" id="new-post-title" value="${title}">
		</div>
		<div class="new-post-contents textarea" id="new-post-text" contenteditable="true">
			${text}
		</div>
		<div class="new-post-image-area" id="new-post-image-area">
			<h4>The images you upload are located at the top of the post.</h4>
			<button onclick="addFileInput();">ADD IMAGE</button>
		</div>
		<div class="new-post-btn">
			<c:choose>
				<c:when test="${id != null}">
					<button onclick="modifyPost(${id})">MODIFY</button>
				</c:when>
				<c:otherwise>
					<button onclick="writePost();">ADD</button>
				</c:otherwise>
			</c:choose>
			<button type="reset">BACK</button>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/new-post.js"></script>

</body>
</html>
