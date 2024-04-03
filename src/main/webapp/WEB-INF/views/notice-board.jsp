<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE-BOARD</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>NOTICE</h1>
		<p>Check out notice related to our site!</p>
	</section>
	<section class="notice-search-area">
		<form action="/notice" method="POST">
			<div class="notice-search-box">
				<select name="sort">
					<option value="title">TITLE</option>
					<option value="contents">CONTENTS</option>
				</select>
				<input type="text" name="query" placeholder="Search notice">
				<button type="submit">SEARCH</button>
			</div>
		</form>
	</section>
	<div class="notice-board-area">
		<div class="notice-board-info">
			<div class="notice-board-area">
				<div class="notice-board-top">
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
				<div class="notice-in-board">
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
		</div>
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</div>
</main>
<footer>
	FOOTER
</footer>
</body>
</html>
