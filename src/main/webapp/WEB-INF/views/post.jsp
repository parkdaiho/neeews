<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div class="post-view-title">
	${post.title}
</div>
<div class="post-view-contents">
	<div class="post-view-images">
		<c:forEach var="savedFileName" items="${post.savedFileNames}">
			<c:url var="image" value="/post-image">
				<c:param name="id" value="${post.id}"></c:param>
				<c:param name="savedName" value="${savedFileName}"></c:param>
			</c:url>
			<img src="${image}">
		</c:forEach>
	</div>
	${post.contents}
</div>
<div>
	<textarea id="comment-create-contents">

	</textarea>
	<button id="comment-create-button">댓글작성</button>
</div>
<div id="comments-area">
	<jsp:include page="comments-area.jsp"></jsp:include>
</div>
</body>
</html>
