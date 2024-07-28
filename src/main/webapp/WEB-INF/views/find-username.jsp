<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>FIND-USERNAME</title>
</head>
<body>
	<div class="find-user-info-menu">
		<ul>
			<li><a href="/find-username">FIND-USERNAME</a></li>
			<li><a href="/find-password">FIND-PASSWORD</a></li>
		</ul>
	</div>
	<div class="find-username-area">
		<div class="find-username-info">
			<h2>FIND USERNAME</h2>
			<p>Enter the email when you signed up.</p>
		</div>
		<div class="find-username-input">
			<label>EMAIL</label>
			<input type="email" id="email">
		</div>
		<div class="find-username-input" id="" style="display: none;">
			<label>CODE</label>
			<input type="text" id="code">
		</div>
		<div class="find-username-btn-area">
			<button onclick="" id="find-username-btn">SEND</button>
		</div>
	</div>
</body>
</html>
