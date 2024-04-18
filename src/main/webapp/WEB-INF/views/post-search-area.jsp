<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>post-search-area</title>
</head>
<body>
<div class="post-search-area">
	<form action="/posts" method="GET">
		<div class="post-search-box">
			<select name="searchSort">
				<c:choose>
					<c:when test="${searchSort == 'text'}">
				<option value="title">TITLE</option>
				<option value="text" selected>CONTENTS</option>
				<option value="writer">WRITER</option>
					</c:when>
					<c:when test="${searchSort == 'writer'}">
						<option value="title">TITLE</option>
						<option value="text">CONTENTS</option>
						<option value="writer" selected>WRITER</option>
					</c:when>
					<c:otherwise>
						<option value="title" selected>TITLE</option>
						<option value="text">CONTENTS</option>
						<option value="writer">WRITER</option>
					</c:otherwise>
				</c:choose>
			</select>
			<input type="text" name="query" value="${query}">
			<button type="submit">SEARCH</button>
		</div>
	</form>
</div>
</body>
</html>
