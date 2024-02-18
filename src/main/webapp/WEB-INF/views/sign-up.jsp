<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>회원가입</h1>
<form action="/sign-up" method="post">
  <input type="text" id="username" placeholder="USERNAME">
  <input type="password" id="password" placeholder="PASSWORD">
  <input type="text" id="nickname" placeholder="NICKNAME">
  <input type="text" id="email" placeholder="EMAIL">
  <button type="button" id="sign_up_btn">가입하기</button>
</form>
<script src="/js/sign-up.js"></script>
</body>
</html>
