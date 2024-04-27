<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>SIGN-UP</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>SIGN-UP</h1>
		<p>Please become a member of our site!</p>
	</section>
	<section class="sign-up-area">
		<div class="sign-up-username">
			USERNAME : <input type="text" id="username" onchange="changeUsername();">
			<button onclick="usernameValidCheck();">CHECK</button>
			<input type="hidden" id="username-valid-flag" value="unchecked">
			<div class="username-valid-check-message" id="username-valid-check-message"></div>
		</div>
		<div class="sign-up-password">
			PASSWORD : <input type="password" id="password" onchange="changePassword();">
			CONFIRM-PASSWORD : <input type="password" id="confirm-password" onchange="changePassword();">
			<input type="hidden" id="confirm-password-valid-flag" value="unchecked">
			<div class="password-valid-check-message" id="password-valid-check-message"></div>
		</div>
		<div class="sign-up-nickname">
			NICKNAME : <input type="text" id="nickname" onchange="changeNickname();">
			<button onclick="nicknameValidCheck();">CHECK</button>
			<input type="hidden" id="nickname-valid-flag" value="unchecked">
			<div class="nickname-valid-check-message" id="nickname-valid-check-message"></div>
		</div>
		<div class="sign-up-email">
			<c:choose>
				<c:when test="${email != null}">
					EMAIL : <input type="email" id="email" value="${email}" readonly>
					<input type="hidden" id="provider" value="${provider}">
					<input type="hidden" id="email-dup-flag" value="checked">
				</c:when>
				<c:otherwise>
					EMAIL : <input type="email" id="email" onchange="changeEmail();">
					<input type="hidden" id="provider" value="SELF">
					<button onclick="emailDupCheck();">CHECK</button>
					<input type="hidden" id="email-dup-flag" value="unchecked">
					<div class="email-valid-check-message" id="email-valid-check-message"></div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="sign-up-btn">
			<button onclick="signUp();">SIGN-UP</button>
			<button onclick="location.replace('/login');">BACK</button>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/sign-up.js"></script>

</body>
</html>
