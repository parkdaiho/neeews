<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/css.css">
	<title>MEMBERSHIP</title>
</head>
<body onload="setSortSelected(Role.ADMIN)">
<div class="membership-area">
	<div class="membership-info">
		<div class="membership-total-users">
			TOTAL ${totalElements} USERS
		</div>
	</div>
	<div class="user-search-area">
		<form action="/users" method="post">
			<div class="user-search-box">
				<input type="text" placeholder="Search username">
			</div>
			<button type="submit">SEARCH</button>
			<button type="reset">RESET</button>
		</form>
	</div>
	<div class="membership-users-area">
		<div class="membership-users-sort">
			<select id="membership-users-sort-select"  onchange="getUsersBySort(this.value);">
				<option value="ALL">ALL</option>
				<option value="ADMIN">ADMINISTRATOR</option>
				<option value="MANAGER">MANAGER</option>
				<option value="USER">USER</option>
			</select>
		</div>
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
			<c:forEach var="user" items="${users}">
				<div class="user-id">
						${user.id}
				</div>
				<div class="user-username">
						${user.username}
				</div>
				<div class="user-email">
						${user.email}
				</div>
				<div class="user-created-at">
						${user.createdAt}
				</div>
				<div class="user-modified-at">
						${user.modifiedAt}
				</div>
				<div class="user-role">
					<select onload="setSelected('${user.role}')">
						<option value="ADMIN">ADMIN</option>
						<option value="MANAGER">MANAGER</option>
						<option value="USER">USER</option>
					</select>
				</div>
				<div class="user-btn">
					<button>MODIFY</button>
					<button>WITHDRAW</button>
				</div>
			</c:forEach>
		</div>
	</div>
	<c:if test="${totalElements != 0}">
		<%@ include file="board-pagination.jsp" %>
	</c:if>
</div>
</body>
</html>
