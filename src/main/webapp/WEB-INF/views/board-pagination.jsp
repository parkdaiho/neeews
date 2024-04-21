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
			<li><a href="javascript: return fail;" onclick="getPages(1, '${order}', '${searchSort}' , '${query}');"><<</a></li>
			<li><a href="javascript: return fail;" onclick="getPages(${previousPage}, '${order}', '${searchSort}', '${query}');"><</a></li>
			<c:forEach var="i" begin="${startNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
				<c:choose>
					<c:when test="${i == page}">
						<li><a href="javascript: return fail;" class="on">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript: return fail;" onclick="getPages(${i}, '${order}', '${searchSort}', '${query}');">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="javascript: return fail;" onclick="getPages(${nextPage}, '${order}', '${searchSort}', '${query}');">></a></li>
			<li><a href="javascript: return fail;" onclick="getPages(${totalPages}, '${order}', '${searchSort}', '${query}');">>></a></li>
		</ul>
	</div>
</body>
</html>
