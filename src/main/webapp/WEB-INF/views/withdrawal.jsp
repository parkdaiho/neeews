<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>WITHDRAWAL</title>
</head>
<body>
<form action="/user">
	<input type="hidden" name="_method" value="DELETE">
	<div class="withdrawal-area">
		<div class="withdrawal-password">
			PASSWORD : <input type="password">
		</div>
		<div class="withdrawal-btn">
			<button type="submit">WITHDRAWAL</button>
		</div>
	</div>
</form>
</body>
</html>
