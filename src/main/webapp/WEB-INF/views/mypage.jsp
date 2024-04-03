<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		<p>You can modify the information related to the membership and leave the membership on this page.</p>
		<p>Thank you for your efforts on our site.</p>
	</section>
	<div class="my-page-menu">
		<ul>
			<li><a href="">MEMBER-INFO</a></li>
			<li><a href="">WITHDRAWAL</a></li>
			<li><a href="">MEMBERSHIP</a></li>
			<li><a href="">NEW-NOTICE</a></li>
		</ul>
	</div>
	<section class="my-page-area">
		MY-PAGE-AREA
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
