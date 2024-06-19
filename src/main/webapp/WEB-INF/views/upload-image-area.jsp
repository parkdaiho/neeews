<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>UPLOAD-IMAGE-AREA</title>
</head>
<body>
<div class="upload-image-description">
	<span>The images you upload are located at the top of the post.</span>
	<button onclick="addFileInput();">ADD IMAGE</button>
</div>
<div class="upload-image-area" id="upload-image-area">

</div>

<script src="/js/upload-image.js"></script>

</body>
</html>
