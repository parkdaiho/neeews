<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>WITHDRAWAL</title>
</head>
<body>
<div class="withdrawal-area">
	<div class="withdrawal-info-area">
		<h2>WITHDRAWAL</h2>
		<p>You can withdraw from our membership.</p>
	</div>
	<div class="withdrawal-password">
		<label>PASSWORD</label>
		<input type="password" id="password">
	</div>
	<div class="withdrawal-btn">
		<button type="button" onclick="withdrawal(${id});">WITHDRAWAL</button>
	</div>
</div>
</body>
</html>
