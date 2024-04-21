<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>NOTICE-SEARCH-AREA</title>
</head>
<body>
<form action="/notice-list" method="GET">
	<div class="notice-search-box">
		<select name="searchSort" id="search-sort">
			<c:choose>
				<c:when test="${searchSort == 'text'}">
					<option value="title">TITLE</option>
					<option value="text" selected>CONTENTS</option>
				</c:when>
				<c:otherwise>
					<option value="title" selected>TITLE</option>
					<option value="text">CONTENTS</option>
				</c:otherwise>
			</c:choose>
		</select>
		<input type="hidden" name="order" value="${order}">
		<input type="text" name="query" id="query" value="${query}">
		<button type="submit">SEARCH</button>
	</div>
</form>
</body>
</html>
