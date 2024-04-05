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
	<section class="page-info-area">
		<h1>LOGIN</h1>
		<p>Login and do various activities!</p>
	</section>
	<section class="login-area">
		<form action="/login" method="post">
			<div class="login-username">
				USERNAME : <input type="text" name="username">
			</div>
			<div class="login-password">
				PASSWORD : <input type="password" name="password">
			</div>
			<div class="login-btn">
				<button type="submit">LOGIN</button>
				<button>RESET</button>
			</div>
		</form>
		<div class="login-oauth2">
			<a href="/oauth2/authorization/google"><img src="/img/oauth2-google.png"></a>
		</div>
		<div class="login-sign-up">
			<a href="/sign-up">If you're not a member yet, please sign up!</a>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
