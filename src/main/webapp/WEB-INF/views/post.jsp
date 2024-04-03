<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>POST</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>COMMUNITY</h1>
		<p>Read the post and share your opinions with people in the comments!</p>
	</section>
	<section class="post-search-area">
		<jsp:include page="post-search-area.jsp"></jsp:include>
	</section>
	<section class="post-area">
		<div class="post-info">
			<div class="post-title">
				TITLE-1
			</div>
			<div class="post-writer">
				aaa
			</div>
			<div class="post-created-at">
				2024-03-28 16:17:28
			</div>
		</div>
		<div class="post-contents">
			<div class="post-contents-images">

			</div>
			<div class="post-contents-textarea">
				CONTENTS
			</div>
		</div>
		<div class="post-btn">
			<button>MODIFY</button>
			<button>DELETE</button>
		</div>
		<div class="post-contents">
			<div class="post-image-area">
				<img src="#">
			</div>
			<div class="post-contents-textarea">
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
