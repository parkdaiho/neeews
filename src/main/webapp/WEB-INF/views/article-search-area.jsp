<%--
  Created by IntelliJ IDEA.
  User: parkdaeho
  Date: 4/3/24
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>ARTICLE-SEARCH-BOX</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<form action="/articles" method="post">
	<div class="article-search-box">
		<select name="sort">
			<option value="sim">정확도순</option>
			<option value="date">최신순</option>
		</select>
		<input type="text" name="query" placeholder="Search News">
		<button type="submit">SEARCH</button>
	</div>
</form>
</body>
</html>
