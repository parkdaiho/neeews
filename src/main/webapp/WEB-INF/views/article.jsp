<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
	<script>
		const id = ${article.id};
		const domain = "${domain}";
	</script>
</head>
<body>
<div>
	<div>
		${article.title}
	</div>
	<div>
		<c:choose>
			<c:when test="${article.isProvided}">
				${article.texts}
			</c:when>
			<c:otherwise>
				제공되지 않는 기사입니다.
				<a href="${article.originalLink}">원문으로 이동</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div id="comments-area">
		<jsp:include page="comments-area.jsp"></jsp:include>
	</div>
</div>
</body>
</html>
