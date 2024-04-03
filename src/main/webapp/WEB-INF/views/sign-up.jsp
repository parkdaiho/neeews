<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<form action="/sign-up" method="post">
			<div class="sign-up-username">
				<input type="text" name="username">
			</div>
			<div class="sign-up-password">
				<input type="password" name="password">
			</div>
			<div class="sign-up-nickname">
				<input type="text" name="nickname">
			</div>
			<div class="sign-up-email">
				<input type="email" name="email">
			</div>
			<div class="sign-up-btn">
				<button type="submit">SIGN-UP</button>
				<button>BACK</button>
			</div>
		</form>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
