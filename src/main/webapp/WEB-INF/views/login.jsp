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
						<input type="text" name="username" placeholder="USERNAME">
					</li>
					<li>
						<input type="password" name="password" placeholder="PASSWORD">
					</li>
				</ul>
				<div class="login-btn">
					<div class="save-username">
						<label for="save-username">아이디 저장</label>
						<input type="checkbox" id="save-username">
					</div>
					<button type="submit">LOGIN</button>
				</div>
			</form>
		</section>
		<section class="login-sign-up">
			If you're not a member yet, please <a href="/sign-up">sign up</a>!
		</section>
		<section class="login-oauth2">
			<a href="/oauth2/authorization/google"><img src="/img/oauth2-google.png"></a>
		</section>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
