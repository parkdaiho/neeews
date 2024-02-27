<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div>
  <c:forEach var="item" items="${items}">
    <div>
    <input type="text" value="${item.title}">
      <div>
        ${item.description}
      </div>
    <input type="text" value="${item.pubDate}">
    </div>
  </c:forEach>
</div>
</body>
</html>
