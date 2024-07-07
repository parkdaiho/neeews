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
		<h1>NOTICE</h1>
		<p>Please, Copyedit contents before adding notice.</p>
	</section>
	<section class="new-post-area">
		<div class="new-notice-title">
			<span>TITLE</span>
			<input type="text" id="new-notice-title" value="${title}">
		</div>
		<div class="new-notice-fixed-flag">
			<span>IS-FIXED</span>
			<c:choose>
				<c:when test="${isFixed == 'true'}">
					<input type="checkbox" id="new-notice-isFixed" checked>
				</c:when>
				<c:otherwise>
					<input type="checkbox" id="new-notice-isFixed">
				</c:otherwise>
			</c:choose>
		</div>
		<div class="new-post-textarea">
			<div class="new-post-text" id="new-notice-text" contenteditable="true">
				${text}
			</div>
		</div>

		<jsp:include page="upload-image-area.jsp"></jsp:include>

	</section>
	<section class="new-post-btn">
		<c:choose>
			<c:when test="${id != null}">
				<button onclick="modifyNotice(${id});">MODIFY</button>
			</c:when>
			<c:otherwise>
				<button onclick="writeNotice();">ADD</button>
			</c:otherwise>
		</c:choose>
		<button onclick="location.replace('/notice-list');">LIST</button>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/new-notice.js"></script>

</body>
</html>
