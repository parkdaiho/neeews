<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<input type="text" value="${article.title}">
<div id="contents">
  ${article.contents}
</div>
<input type="text" value="원문 url: ${article.originalLink}">
<input type="text" value="${article.pubDate}">
<div>

</div>
</body>
</html>
