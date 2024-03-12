<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div>
	<div class="post-write-title">
		<input type="text" id="post-write-title">
	</div>
	<div class="post-write-contents">
		<textarea id="post-write-contents">

		</textarea>
	</div>
	<div id="post-upload-area">
		<button id="add-file-btn" onclick="addFileInput()">파일 추가</button>
	</div>
	<button onclick="writePost();">WRITE</button>
</div>
<script src="/js/post-write.js"></script>
</body>
</html>
