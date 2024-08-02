<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>CHANGE-PASSWORD</title>
</head>
<body>
	<div class="find-info-area">
		<div class="find-user-info-menu">
			<ul>
				<li><a href="javascript: return false;" onclick="getFindUsernamePage();">FIND-USERNAME</a></li>
				<li><a class="menu-on" href="javascript: return false;" onclick="getFindPasswordPage();">FIND-PASSWORD</a></li>
			</ul>
		</div>
		<div class="find-user-info-right-area">
			<div class="find-user-info-info">
				<h2>CHANGE-PASSWORD</h2>
				<p>Change to the password you want to use.</p>
			</div>
			<div class="change-password-input">
				<label>PASSWORD</label>
				<input type="password" id="password" onchange="checkRegex();">
				<input type="hidden" id="regex-check" value="false">
			</div>
			<div class="change-password-input">
				<label>CONFIRM-PASSWORD</label>
				<input type="password" id="confirm-password" onchange="checkConfirm();">
				<input type="hidden" id="confirm-check" value="unchecked">
			</div>
			<div class="change-password-valid-check-message" id="change-password-valid-check-message"
					 style="display: none;"></div>
			<div class="change-password-btn-area">
				<button onclick="changePassword();">CHANGE</button>
			</div>
		</div>
	</div>
</body>
</html>
