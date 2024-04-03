<%--
  Created by IntelliJ IDEA.
  User: parkdaeho
  Date: 4/3/24
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NEW-POST</title>
	<script>document.execCommand('defaultParagraphSeparator', false, 'br');</script>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<main>
	<section class="page-info-area">
		<h1>POST</h1>
		<p>Add a post and share your opinion with people!</p>
	</section>
	<div class="new-post-area">
		<div class="new-post-title">
			<input type="text" placeholder="TITLE">
		</div>
		<div class="new-post-contents textarea" contenteditable="true">

		</div>
		<div class="new-post-file-area">
			<div class="new-post-input">

			</div>
			<div class="new-post-image-thumbnail">

			</div>
		</div>
		<div class="new-post-btn">
			<button>ADD</button>
			<button>MODIFIY</button>
			<button>BACK</button>
		</div>
	</div>
</main>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>
