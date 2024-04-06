<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<title>INDEX-MIDDLE-CONTENTS</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<c:choose>
	<c:when test="${domain == 'article'}">
		<div class="index-middle-info" id="item-articles-part">
			<h2>ARTICLES</h2>
			<select onchange="getIndexArticles(this.value);">
				<c:choose>
					<c:when test="${order == 'views'}">
						<option value="views" selected>MOST-VIEWED</option>
						<option value="popularity">MOST-POPULAR</option>
					</c:when>
					<c:otherwise>
						<option value="views">MOST-VIEWED</option>
						<option value="popularity" selected>MOST-POPULAR</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div class="index-middle-contents">
			<ul>
				<c:forEach var="item" items="${items}">
					<li><a href="${item.link}">${item.title}</a><span>${item.figure}</span></li>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div class="index-middle-info" id="item-posts-part">
			<h2>POSTS</h2>
			<select onchange="getIndexPosts(this.value);">
				<c:choose>
					<c:when test="${order == 'views'}">
						<option value="views" selected>MOST-VIEWED</option>
						<option value="popularity">MOST-POPULAR</option>
					</c:when>
					<c:otherwise>
						<option value="views">MOST-VIEWED</option>
						<option value="popularity" selected>MOST-POPULAR</option>
					</c:otherwise>
				</c:choose>
			</select>
		</div>
		<div class="index-middle-contents">
			<ul>
				<c:forEach var="item" items="${items}">
					<li><a href="${item.link}">${item.title}</a><span>${item.figure}</span></li>
				</c:forEach>
			</ul>
		</div>
	</c:otherwise>
</c:choose>>
</body>
</html>