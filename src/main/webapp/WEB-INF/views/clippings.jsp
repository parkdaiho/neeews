<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>CLIPPINGS</title>
</head>
<body>
<div class="clippings-area">
	<div class="clippings-info">
		<div class="clippings-total-articles">
			TOTAL <b>999</b> CLIPPINGS
		</div>
	</div>
	<div class="clippings-list-area">
		<div class="clipping-in-list">
			<div class="clipping-title">
				...
			</div>
			<div class="clipping-description">
				...
			</div>
			<div class="clipping-pubdate">
				...
			</div>
		</div>
	</div>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</div>
</body>
</html>
