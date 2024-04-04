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
			USERNAME : <input type="text" value="${username}" readonly>
		</div>
		<div class="member-info-password">
			PASSWORD : <input type="password">
			CONFIRM-PASSWORD : <input type="password">
		</div>
		<div class="member-info-nickname">
			NICKNAME : <input type="text" value="${nickname}">
			<button>CHECK</button>
		</div>
		<div class="member-info-email">
			EMAIL : <input type="email" value="${email}" readonly>
		</div>
	</div>
	<div class="member-info-btn">
		<button type="submit">MODIFY</button>
		<button>BACK</button>
	</div>
</form>
</body>
</html>
