<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div>
	<c:forEach var="item" items="${items}" varStatus="status">
		<div class="item" style="border: 1px solid black" onclick="showArticle(${status.index});">
			<input type="hidden" id="item_link_${status.index}" value="${item.link}">
			<input type="hidden" id="item_originalLink_${status.index}" value="${item.originallink}">
			<div class="item_title" id="item_title_${status.index}">
					${item.title}
			</div>
			<div class="item_description" id="item_description_${status.index}">
					${item.description}
			</div>
			<div class="item_pubDate" id="item_pubDate_${status.index}">
					${item.pubDate}
			</div>
			<div>
				<a href="${item.link}">링크</a>
			</div>
		</div>
	</c:forEach>
</div>
<script src="/js/article.js"></script>
</body>
</html>
