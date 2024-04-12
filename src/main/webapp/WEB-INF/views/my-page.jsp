<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MY-PAGE</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>MY-PAGE</h1>
		<c:choose>
			<c:when test="${!isUser}">
				<p>Thank you for your efforts on our site.</p>
			</c:when>
			<c:otherwise>
				<p>You can modify the information related to the membership and leave the membership on this page.</p>
			</c:otherwise>
		</c:choose>
	</section>
	<div class="my-page-menu">
		<ul>
			<li><a href="javascript:return false;" onclick="getMemberInfoPage();">MEMBER-INFO</a></li>
			<li><a href="javascript:return false;" onclick="getWithdrawalPage(${id});">WITHDRAWAL</a></li>
			<li><a href="javascript:return false;" onclick="getMembershipPage(1, 'ALL', null);">MEMBERSHIP</a></li>
		</ul>
	</div>
	<section class="my-page-area" id="my-page-area">
		<jsp:include page="member-info.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

<script src="/js/my-page.js"></script>
<script src="/js/membership.js"></script>
<script src="/js/member-info.js"></script>
<script src="/js/withdrawal.js"></script>

</body>
</html>
