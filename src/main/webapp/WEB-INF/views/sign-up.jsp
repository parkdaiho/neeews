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
			USERNAME : <input type="text" id="username">
			<button>CHECK</button>
		</div>
		<div class="sign-up-password">
			PASSWORD : <input type="password" id="password">
			CONFIRM-PASSWORD : <input type="password" id="confirm-password">
		</div>
		<div class="sign-up-nickname">
			NICKNAME : <input type="text" id="nickname">
			<button>CHECK</button>
		</div>
		<div class="sign-up-email">
			<c:choose>
				<c:when test="${email != null}">
					EMAIL : <input type="email" id="email" value="${email}" readonly>
					<input type="hidden" id="provider" value="${provider}">
				</c:when>
				<c:otherwise>
					EMAIL : <input type="email" id="email">
					<input type="hidden" id="provider" value="SELF">
					<button>CHECK</button>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="sign-up-btn">
			<button onclick="signUp();">SIGN-UP</button>
			<button>BACK</button>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/sign-up.js"></script>

</body>
</html>
