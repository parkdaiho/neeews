<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>post-search-area</title>
</head>
<body>
<div class="post-search-area">
	<form action="/posts" method="POST">
		<div class="post-search-box">
			<select name="sort">
				<option value="title">TITLE</option>
				<option value="contents">CONTENTS</option>
				<option value="writer">WRITER</option>
			</select>
			<input type="text" id="query">
			<button type="submit">SEARCH</button>
		</div>
	</form>
</div>
</body>
</html>
