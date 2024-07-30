<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>FOUND-USERNAME</title>
</head>
<body>
	<div class="find-info-area">
		<div class="find-user-info-menu">
			<ul>
				<li><a class="menu-on" href="javascript: return false;" onclick="getFindUsernamePage();">FIND-USERNAME</a></li>
				<li><a href="javascript: return false;" onclick="getFindPasswordPage();">FIND-PASSWORD</a></li>
			</ul>
		</div>
		<div class="find-user-info-right-area">
			<div class="find-username-result">
				아이디는 <span class="found-username">${username}</span> 입니다.
			</div>
			<div class="find-username-result-btn-area">
				<button onclick="getLoginPage();">LOGIN</button>
			</div>
		</div>
	</div>
</body>
</html>
