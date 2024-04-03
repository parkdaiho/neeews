<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="index-info-area">
		<h1>Find an article and share your opinion!</h1>
		<form action="/articles" method="post">
			<div class="index-search-box">
				<select name="domain">
					<option value="article" selected>ARTICLE</option>
					<option value="post">POST</option>
				</select>
				<select name="sort">
					<option value="sim">정확도순</option>
					<option value="date">최신순</option>
				</select>
				<!-- <select name="sort">
					<option value="title">제목</option>
					<option value="contents">내용</option>
					<option value="writer">작성자</option>
				</select> -->
				<input type="text" name="query" placeholder="Search ...">
				<button type="submit">검색</button>
			</div>
		</form>
	</section>
	<section class="index-middle-area">
		<div class="index-middle-part">
			<div class="index-middle-info">
				<h2>ARTICLES</h2>
				<select onchange="">
					<option value="views">조회수순</option>
					<option value="popularity">인기순</option>
				</select>
			</div>
			<div class="index-middle-contents">
				<ul>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
				</ul>
			</div>
		</div>
		<div class="index-middle-part">
			<div class="index-middle-info">
				<h2>POSTS</h2>
				<select onchange="">
					<option value="views">조회수순</option>
					<option value="popularity">인기순</option>
				</select>
			</div>
			<div class="index-middle-contents">
				<ul>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
					<li><a href="#">title1</a><span>999</span></li>
				</ul>
			</div>
		</div>
	</section>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
