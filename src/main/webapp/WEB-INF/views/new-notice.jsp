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
	<div class="new-notice-area">
		<div class="new-notice-title">
			TITLE : <input type="text" id="new-notice-title" value="${title}">
		</div>
		<div class="new-notice-fixed-flag">
			IS-FIXED : <input type="checkbox" id="new-notice-isFixed">
		</div>
		<div class="new-notice-textarea">
			<div id="new-notice-text" contenteditable="true">
				${text}
			</div>
		</div>

		<jsp:include page="upload-image-area.jsp"></jsp:include>

	</div>
	<div class="new-notice-btn">
		<c:choose>
			<c:when test="${id != null}">
				<button onclick="modifyNotice(${id});">MODIFY</button>
			</c:when>
			<c:otherwise>
				<button onclick="writeNotice();">ADD</button>
			</c:otherwise>
		</c:choose>
		<button onclick="location.replace('/notice-list');">LIST</button>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/new-notice.js"></script>

</body>
</html>
