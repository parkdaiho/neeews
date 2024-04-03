<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MEMBER-INFO</title>
</head>
<body>
<form action="/user">
	<input type="hidden" name="_method" value="PUT">
	<div class="member-info-area">
		<div class="member-info-username">
			<input type="text" readonly>
		</div>
		<div class="member-info-password">
			<input type="password">
			<input type="password">
		</div>
		<div class="member-info-nickname">
			<input type="text">
			<button>CHECK</button>
		</div>
		<div class="member-info-email">
			<input type="email" readonly>
		</div>
	</div>
	<div class="member-info-btn">
		<button type="submit">MODIFY</button>
		<button>BACK</button>
	</div>
</form>
</body>
</html>
