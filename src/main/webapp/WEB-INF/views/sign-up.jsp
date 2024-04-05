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
		<form action="/sign-up" method="post">
			<div class="sign-up-username">
				USERNAME : <input type="text" name="username">
				<button>CHECK</button>
			</div>
			<div class="sign-up-password">
				PASSWORD : <input type="password" name="password">
				CONFIRM-PASSWORD : <input type="password" name="password" onchange="">
			</div>
			<div class="sign-up-nickname">
				NICKNAME : <input type="text" name="nickname">
				<button>CHECK</button>
			</div>
			<div class="sign-up-email">
				<c:choose>
					<c:when test="${email != null}">
						EMAIL : <input type="email" name="email" value="${email}" readonly>
						<input type="hidden" name="provider" value="${provider}">
					</c:when>
					<c:otherwise>
						EMAIL : <input type="email" name="email">
						<button>CHECK</button>
					</c:otherwise>
				</c:choose>
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
