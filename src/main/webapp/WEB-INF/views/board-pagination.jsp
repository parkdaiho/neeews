<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>BOARD-PAGINATION</title>
</head>
<body>
	<div class="board-pagination">
		<ul>
			<li><a href="javascript: return fail;" onclick="getPages(1, '${sort}', '${query}');"><<</a></li>
			<li><a href="javascript: return fail;" onclick="getPages(${previousPage}, '${sort}', '${query}');"><</a></li>
			<c:forEach var="i" begin="${firstNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
			<li><a href="javascript: return fail;" onclick="getPages(${i}, '${sort}', '${query}');">${i}</a></li>
			</c:forEach>
			<li><a href="javascript: return fail;" onclick="getPages(${nextPage}, '${sort}', '${query}');">></a></li>
			<li><a href="javascript: return fail;" onclick="getPages(${totalPages}, '${sort}', '${query}');">>></a></li>
		</ul>
	</div>
</body>
</html>
