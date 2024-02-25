<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div>
  <c:forEach var="article" items="articles">
    <input type="text" value="${article.title}">
    <input type="text" value="${article.description}">
    <input type="text" value="${article.pubDate}">
  </c:forEach>
</div>
</body>
</html>
