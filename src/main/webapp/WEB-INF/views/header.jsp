<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body onload="storeAccessToken('${token}');">
	<div class="logo">
		<a href="/">LOGO</a>
	</div>
	<div class="middle-area">
		<ul>
			<li><a href="/">HOME</a></li>
			<li><a href="/articles">ARTICLES</a></li>
			<li><a href="/posts">COMMUNITY</a></li>
			<li><a href="/notice">NOTICE</a></li>
		</ul>
	</div>
	<div class="right-area">
		<ul>
			<c:choose>
				<c:when test="${nickname != null}">
					<li><span>${nickname}</span> 님, 환영합니다.</li>
					<li><a href="/my-page">MY-PAGE</a></li>
					<li><a href="javascript:return false;" onclick="logout();">LOGOUT</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/login">LOGIN</a></li>
					<li><a href="/sign-up">SIGN-UP</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

<script src="/js/header.js"></script>
<script src="/js/application.js"></script>

</body>
</html>
