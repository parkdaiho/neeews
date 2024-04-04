<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NEW-NOTICE</title>
</head>
<body>
<div class="new-notice-area">
	<div class="new-notice-title">
		TITLE : <input type="text" id="notice-title">
	</div>
	<div class="new-notice-contents">
		contents : <div contenteditable="true" id="notice-contents"></div>
	</div>
</div>
<div class="new-notice-btn">
	<button>ADD</button>
	<button>MODIFY</button>
	<button>BACK</button>
</div>
</body>
</html>
