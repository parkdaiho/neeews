<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>NOTICE</h1>
		<p>Read notice and give me your opinion!</p>
	</section>
	<section class="notice-search-area">

	</section>
	<section class="notice-area">
		<div class="notice-info">
			<div class="notice-title">
				TITLE
			</div>
			<div class="notice-created-at">
				2024-03-29 18:00:00
			</div>
			<div class="notice-modified-at">
				2024-03-29 18:00:00
			</div>
		</div>
		<div class="notice-contents">
			<div class="notice-contents-images">

			</div>
			<div class="notice-contents-textarea">
				CONTENTS
			</div>
		</div>
	</section>
	<section class="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
