<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>POLL-AREA</title>
</head>
<body>
<button onclick="poll(${id}, true);">👍 ${good}</button>
<button onclick="poll(${id}, false)">👎 ${bad}</button>

<script src="/js/poll.js"></script>

</body>
</html>
