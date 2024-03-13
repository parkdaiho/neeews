<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div class="post-view-title">

</div>
<div class="post-view-contents">
	<div class="post-view-images">
		<img src="#" alt="post-img">
	</div>

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
