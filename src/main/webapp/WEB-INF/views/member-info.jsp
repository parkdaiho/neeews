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
		PASSWORD : <input type="password" id="password" onchange="changePassword();">
		CONFIRM-PASSWORD : <input type="password" id="confirm-password" onchange="changePassword();">
		<input type="hidden" id="confirm-password-valid-flag" value="unchecked">
	</div>
	<div class="member-info-nickname">
		NICKNAME : <input type="text" value="${nickname}" id="nickname" onchange="changeNickname('${nickname}');">
		<button type="button" onclick="nicknameDupCheck();">CHECK</button>
		<input type="hidden" id="nickname-valid-flag" value="checked">
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
