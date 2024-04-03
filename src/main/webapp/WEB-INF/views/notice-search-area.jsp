<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE-SEARCH-AREA</title>
</head>
<body>
<div class="notice-search-area">
	<form action="/notice" method="POST">
		<div class="notice-search-box">
			<select name="sort">
				<option value="title">TITLE</option>
				<option value="contents">CONTENTS</option>
			</select>
			<input type="text" id="query">
			<button type="submit">SEARCH</button>
		</div>
	</form>
</div>
</body>
</html>
