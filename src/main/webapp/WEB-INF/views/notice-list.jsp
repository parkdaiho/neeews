<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE-LIST</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>NOTICE</h1>
		<p>Check out notice for using our site!</p>
	</section>
	<section class="notice-search-area">
		<jsp:include page="notice-search-area.jsp"></jsp:include>
	</section>
	<section class="notice-list-area">
		<div class="notice-list-info">
			TOTAL ${totalElements} NOTICE
		</div>
		<div class="notice-list">
			<div class="notice-list-top">
				<div class="notice-title">
					TITLE
				</div>
				<div class="notice-writer">
					WRITER
				</div>
				<div class="notice-created-at">
					CREATED-AT
				</div>
			</div>
			<div class="notice-fixed-in-list">
				<div class="notice-title">
					TITLE-1
				</div>
				<div class="notice-writer">
					ADMIN
				</div>
				<div class="notice-created-at">
					2024-03-28
				</div>
			</div>
			<div class="notice-in-list">
				<div class="notice-title">
					TITLE-1
				</div>
				<div class="notice-writer">
					ADMIN
				</div>
				<div class="notice-created-at">
					2024-03-28
				</div>
			</div>
			...
		</div>
	</section>
	<jsp:include page="board-pagination.jsp"></jsp:include>
</main>
<footer>
	FOOTER
</footer>
</body>
</html>
