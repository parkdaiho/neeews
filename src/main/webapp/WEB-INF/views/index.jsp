<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>index</h1>
<a href="/login">로그인</a>
<c:if test="${userNickname != null}">
<button type="button" id="logout-btn">${loginUser} 로그아웃</button>
</c:if>
  <input type="text" id="search-param">
  <select id="search-sort">
    <option value="sim" selected>정확도순</option>
    <option value="date">최신순</option>
  </select>
  <button type="button" id="search-btn">검색</button>
<script src="/js/header.js"></script>
</body>
</html>
