<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>FIND-USER-INFO</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>FIND USER INFORMATION</h1>
		<p>You can find your username or password this page.</p>
	</section>
	<div class="find-user-info-page" id="find-user-info-area">
		<section class="find-user-info-area">
			<p>What information did you forget?</p>
			<div class="find-user-info-btn-area">
				<div class="find-user-info-btn" onclick="getFindUsernamePage();">
					USERNAME
				</div>
				<div class="find-user-info-btn" onclick="getFindPasswordPage();">
					PASSWORD
				</div>
			</div>
		</section>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/find-user-info.js"></script>
<script src="/js/find-username.js"></script>
<script src="/js/find-password.js"></script>

</body>
</html>
