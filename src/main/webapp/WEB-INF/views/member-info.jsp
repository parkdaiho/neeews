<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MEMBER-INFO</title>
</head>
<body>
<div class="member-info-area">
	<div class="member-info-username">
		USERNAME : <input type="text" value="${username}" readonly>
	</div>
	<div class="member-info-password">
		PASSWORD : <input type="password" id="password" onchange="confirmPassword();">
		CONFIRM-PASSWORD : <input type="password" id="confirm-password" onchange="confirmPassword();">
		<input type="hidden" id="confirm-password-flag" value="false">
	</div>
	<div class="member-info-nickname">
		NICKNAME : <input type="text" value="${nickname}" id="nickname" onchange="changeNickname('${nickname}');">
		<button type="button" onclick="nicknameDupCheck();">CHECK</button>
		<input type="hidden" id="nickname-dup-flag" value="true">
	</div>
	<div class="member-info-email">
		EMAIL : <input type="email" value="${email}" readonly>
	</div>
</div>
<div class="member-info-btn">
	<button type="button" onclick="modifyUser(${id});">MODIFY</button>
	<button>BACK</button>
</div>
</body>
</html>
