<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>ARTICLE-SEARCH-BOX</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<form action="/articles" method="get">
	<div class="article-search-box">
		<select name="sort">
			<c:choose>
				<c:when test="${sort == 'sim'}">
					<option value="sim" selected>MOST-SIMILAR</option>
					<option value="date">LATEST</option>
				</c:when>
				<c:otherwise>
					<option value="sim">MOST-SIMILAR</option>
					<option value="date" selected>LATEST</option>
				</c:otherwise>
			</c:choose>
		</select>
		<input type="text" name="query" placeholder="Search News" value="${query}">
		<button type="submit">SEARCH</button>
	</div>
</form>
</body>
</html>
