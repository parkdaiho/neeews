<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			TOTAL ${totalElements} USERS
		</div>
	</div>
	<div class="user-search-area">
		<div class="user-search-box">
			<select id="search-sort">
				<option value="username">USERNAME</option>
				<option value="nickname">NICKNAME</option>
			</select>
			<input type="text" id="query" placeholder="Search user" value="${query}">
		</div>
		<button onclick="searchUser();">SEARCH</button>
	</div>
	<div class="membership-users-sort">
		<select id="membership-users-sort-select" onchange="getUsersBySort(this.value);">
			<c:choose>
				<c:when test="${sort == 'admin'}">
					<option value="ALL">ALL</option>
					<option value="ADMIN" selected>ADMINISTRATOR</option>
					<option value="MANAGER">MANAGER</option>
					<option value="USER">USER</option>
				</c:when>
				<c:when test="${sort == 'manager'}">
					<option value="ALL">ALL</option>
					<option value="ADMIN">ADMINISTRATOR</option>
					<option value="MANAGER" selected>MANAGER</option>
					<option value="USER">USER</option>
				</c:when>
				<c:when test="${sort == 'user'}">
					<option value="ALL">ALL</option>
					<option value="ADMIN">ADMINISTRATOR</option>
					<option value="MANAGER">MANAGER</option>
					<option value="USER" selected>USER</option>
				</c:when>
				<c:otherwise>
					<option value="ALL" selected>ALL</option>
					<option value="ADMIN">ADMINISTRATOR</option>
					<option value="MANAGER">MANAGER</option>
					<option value="USER">USER</option>
				</c:otherwise>
			</c:choose>
		</select>
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
		<c:forEach var="user" items="${users}">
		<div class="user-in-users">
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
					<c:choose>
						<c:when test="${!isAdmin}">
							<select disabled>
						</c:when>
						<c:otherwise>
							<select id="user-role-${user.id}" onchange="changeRole(${user.id}, '${user.role}', this.value);">
						</c:otherwise>
					</c:choose>
						<c:choose>
							<c:when test="${user.role == 'ADMIN'}">
								<option value="ADMIN" selected>ADMIN</option>
								<option value="MANAGER">MANAGER</option>
								<option value="USER">USER</option>
							</c:when>
							<c:when test="${user.role == 'MANAGER'}">
								<option value="ADMIN">ADMIN</option>
								<option value="MANAGER" selected>MANAGER</option>
								<option value="USER">USER</option>
							</c:when>
							<c:otherwise>
								<option value="ADMIN">ADMIN</option>
								<option value="MANAGER">MANAGER</option>
								<option value="USER" selected>USER</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
				<div class="user-btn">
					<button>WITHDRAW</button>
				</div>
		</div>
		</c:forEach>
	</div>
	<c:if test="${totalElements != 0}">
		<jsp:include page="board-pagination.jsp"></jsp:include>
	</c:if>
</div>
</body>
</html>
