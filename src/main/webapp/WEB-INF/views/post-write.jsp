<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div>
	<div class="post-write-title">
		<input type="text" id="post-write-title" value="${post.title}">
	</div>
	<div class="post-write-contents">
		<textarea id="post-write-contents">
			${post.contents}
		</textarea>
	</div>
	<div id="post-upload-area">
		<button id="add-file-btn" onclick="addFileInput()">파일 추가</button>
	</div>
	<c:choose>
		<c:when test="${post != null}">
			<button onclick="modifyPost(${post.id});">MODIFY</button>
		</c:when>
		<c:otherwise>
			<button onclick="writePost();">WRITE</button>
		</c:otherwise>
	</c:choose>
</div>
<script src="/js/post-write.js"></script>
</body>
</html>
