<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>FIND PASSWORD</title>
</head>
<body>
	<div class="find-user-info-menu">
		<ul>
			<li><a href="/find-username">FIND-USERNAME</a></li>
			<li><a href="/find-password">FIND-PASSWORD</a></li>
		</ul>
	</div>
	<div class="find-password-area">
		<div class="find-password-info">
			<h2>FIND PASSWORD</h2>
			<p>Enter the username and email.</p>
		</div>
		<div class="find-password-input">
			<label>USERNAME</label>
			<input type="text" id="username">
		</div>
		<div class="find-password-input">
			<label>EMAIL</label>
			<input type="email" id="email">
		</div>
		<div class="find-password-input" id="" style="display: none;">
			<label>CODE</label>
			<input type="text" id="code">
		</div>
		<div class="find-password-btn-area">
			<button onclick="" id="find-password-btn">CHECK</button>
		</div>
	</div>
</body>
</html>
