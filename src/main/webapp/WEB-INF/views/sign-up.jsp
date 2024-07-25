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
	<div class="sign-up-page">
		<section class="page-info-area">
			<h1>SIGN-UP</h1>
			<p>Please become a member of our site!</p>
		</section>
		<section class="sign-up-area">
			<div class="sign-up-input">
				<label for="username">USERNAME</label>
				<div class="sign-up-input-button-area">
					<input type="text" id="username" onchange="changeUsername();" autocomplete="false">
					<button class="add" onclick="usernameValidCheck();">CHECK</button>
				</div>
				<input type="hidden" id="username-valid-flag" value="unchecked">
			</div>
			<div class="input-valid-check-message" id="username-valid-check-message" style="display: none;"></div>
			<div class="sign-up-input">
				<label for="password">PASSWORD</label>
				<div class="sign-up-input-button-area">
					<input type="password" id="password" onchange="changePassword();">
				</div>
			</div>
			<div class="sign-up-input">
				<label for="confirm-password">CONFIRM-PASSWORD</label>
				<div class="sign-up-input-button-area">
					<input type="password" id="confirm-password" onchange="changePassword();">
				</div>
				<input type="hidden" id="confirm-password-valid-flag" value="unchecked">
			</div>
			<div class="input-valid-check-message" id="password-valid-check-message" style="display: none;"></div>
			<div class="sign-up-input">
				<label for="nickname">NICKNAME</label>
				<div class="sign-up-input-button-area">
					<input type="text" id="nickname" onchange="changeNickname();" autocomplete="false">
					<button class="add" onclick="nicknameValidCheck();">CHECK</button>
				</div>
				<input type="hidden" id="nickname-valid-flag" value="unchecked">
			</div>
			<div class="input-valid-check-message" id="nickname-valid-check-message" style="display: none;"></div>
				<c:choose>
					<c:when test="${email != null}">
					<div class="sign-up-input">
						<label for="email">EMAIL</label>
						<div class="sign-up-input-button-area">
							<input type="email" id="email" value="${email}" readonly>
							<button class="add" disabled>CHECK</button>
						</div>
						<input type="hidden" id="provider" value="${provider}">
						<input type="hidden" id="email-dup-flag" value="checked">
					</div>
					<div class="input-valid-check-message">
						이메일 인증이 완료되었습니다.
					</div>
					</c:when>
					<c:otherwise>
						<div class="sign-up-input">
							<label for="email">EMAIL</label>
							<div class="sign-up-input-button-area">
								<input type="email" id="email" autocomplete="false">
								<button class="add" onclick="emailValidCheck();" id="email-valid-check-btn">CHECK</button>
							</div>
							<input type="hidden" id="provider" value="SELF">
							<input type="hidden" id="email-valid-flag" value="unchecked">
							<input type="hidden" id="email-auth-flag" value="unchecked">
						</div>
						<div class="sign-up-input" id="email-auth-area" style="display: none;">
							<label for="email-auth-number">CODE</label>
							<div class="sign-up-input-button-area">
								<input type="text" id="email-auth-number" placeholder="Enter emailed authentication number.">
								<button class="add" onclick="emailAuthCheck();">CHECK</button>
							</div>
						</div>
						<div class="input-valid-check-message" id="email-valid-check-message" style="display: none;"></div>
					</c:otherwise>
				</c:choose>
		</section>
		<section class="sign-up-btn">
			<button class="add" onclick="signUp();">SIGN-UP</button>
			<button onclick="location.replace('/login');">BACK</button>
		</section>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/sign-up.js"></script>

</body>
</html>
