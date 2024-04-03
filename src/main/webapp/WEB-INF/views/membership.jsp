<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MEMBERSHIP</title>
</head>
<body>
<div class="membership-area">
	<div class="membership-info">
		<div class="membership-total-users">
			TOTAL 99999999 USERS
		</div>
	</div>
	<div class="user-search-area">
		<form action="/users" method="post">
			<div class="user-search-box">
				<select name="role">
					<option value="ADMIN">ADMINISTRATOR</option>
					<option value="MANAGER">MANAGER</option>
					<option value="USER">USER</option>
				</select>
				<input type="text" placeholder="Search username">
			</div>
			<button type="submit">SEARCH</button>
			<button type="reset">RESET</button>
		</form>
	</div>
	<div class="membership-users-area">
		<div class="users-top">
			<div class="user-id">
				NUM
			</div>
			<div class="user-username">
				USERNAME
			</div>
			<div class="user-email">
				EMAIL
			</div>
			<div class="user-created-at">
				CREATED-AT
			</div>
			<div class="user-modified-at">
				MODIFIED-AT
			</div>
			<div class="user-role">
				ROLE
			</div>
		</div>
		<div class="user-in-users">
			<div class="user-id">
				NUM
			</div>
			<div class="user-username">
				USERNAME
			</div>
			<div class="user-email">
				EMAIL
			</div>
			<div class="user-created-at">
				2024-03-29 19:36:25
			</div>
			<div class="user-modified-at">
				2024-03-29 19:36:25
			</div>
			<div class="user-role">
				<select>
					<option value="ADMIN">ADMIN</option>
					<option value="MANAGER">MANAGER</option>
					<option value="USER">USER</option>
				</select>
			</div>
			<div class="user-btn">
				<button>MODIFY</button>
				<button>WITHDRAW</button>
			</div>
		</div>
	</div>
	<jsp:include page="board-pagination.jsp"></jsp:include>
</div>
</body>
</html>
