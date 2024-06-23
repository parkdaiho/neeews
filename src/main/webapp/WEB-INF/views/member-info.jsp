<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MEMBER-INFO</title>
</head>
<body>
<div class="member-info-area">
	<div class="member-info-input">
		<label>USERNAME</label>
		<div class="member-info-input-button-area">
			<input type="text" value="${username}" readonly>
		</div>
	</div>
	<div class="member-info-input">
		<label for="password">PASSWORD</label>
		<div class="member-info-input-button-area">
			<input type="password" id="password" onchange="changePassword();">
		</div>
	</div>
	<div class="member-info-input">
		<label for="confirm-password">CONFIRM-PASSWORD</label>
		<div class="member-info-input-button-area">
			<input type="password" id="confirm-password" onchange="changePassword();">
		</div>
		<input type="hidden" id="confirm-password-valid-flag" value="unchecked">
	</div>
	<div class="input-valid-check-message" id="password-valid-check-message" style="display: none;"></div>
	<div class="member-info-input">
		<label for="nickname">NICKNAME</label>
		<div class="member-info-input-button-area">
			<input type="text" value="${nickname}" id="nickname" onchange="changeNickname('${nickname}');">
			<button type="button" onclick="nicknameDupCheck('${nickname}');">CHECK</button>
		</div>
		<input type="hidden" id="nickname-valid-flag" value="checked">
	</div>
	<div class="input-valid-check-message" id="nickname-valid-check-message" style="display: none;"></div>
	<div class="member-info-input">
		<label>EMAIL</label>
		<div class="member-info-input-button-area">
			<input type="email" value="${email}" readonly>
		</div>
	</div>
	<div class="member-info-btn">
		<button class="add" type="button" onclick="modifyUser(${id});">MODIFY</button>
	</div>
</div>
</body>
</html>
