<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>LOGIN</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<div class="login-page">
		<section class="page-info-area">
			<h1>LOGIN</h1>
			<p>Login and do various activities!</p>
		</section>
		<section class="login-area">
			<form action="/login" method="POST">
				<ul>
					<li>
						<input type="text" name="username" placeholder="USERNAME" autocomplete="false"
						value="${username}">
					</li>
					<li>
						<input type="password" name="password" placeholder="PASSWORD">
					</li>
				</ul>
				<div class="login-btn">
					<div class="save-username">
						<label for="save-username">아이디 저장</label>
						<c:choose>
							<c:when test="${username != null}">
								<input type="checkbox" id="save-username" name="save-username" value="checked" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="save-username" name="save-username" value="checked">
							</c:otherwise>
						</c:choose>
					</div>
					<button class="add" type="submit">LOGIN</button>
				</div>
			</form>
		</section>
		<section class="login-additional-area">
			<div class="login-additional">
				If you're not our site member yet, please <a href="/sign-up">sign up</a>!
			</div>
			<div class="login-additional">
				If you forget your username or password, <a href="/find-user-info">click here</a>!
			</div>
		</section>
		<section class="login-oauth2">
			<a href="/login/google"><img src="/img/oauth2-google.png"></a>
			<a href="/login/naver"><img src="/img/oauth2-naver.png"></a>
		</section>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
