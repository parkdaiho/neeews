<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>COMMENTS-AREA</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<div class="new-comment-textarea">
	<div class="new-comment-text" contenteditable="true" id="new-comment-text">

	</div>
	<button onclick="addComment();">ADD</button>
</div>
<div class="comments-info">
	<span class="total-comments">TOTAL ${totalElements}</span>
	<select onchange="getCommentsByOrder(this.value);" id="order">
		<c:choose>
			<c:when test="${order == 'earliest'}">
				<option value="latest">LATEST</option>
				<option value="earliest" selected>EARLIEST</option>
				<option value="popular">MOST-POPULAR</option>
			</c:when>
			<c:when test="${order == 'popular'}">
				<option value="latest">LATEST</option>
				<option value="earliest">EARLIEST</option>
				<option value="popular" selected>MOST-POPULAR</option>
			</c:when>
			<c:otherwise>
				<option value="latest" selected>LATEST</option>
				<option value="earliest">EARLIEST</option>
				<option value="popular">MOST-POPULAR</option>
			</c:otherwise>
		</c:choose>
	</select>
</div>
<div class="comment-list">
	<c:forEach var="comment" items="comments">
	<div class="comment-in-comments">
		<div class="comment-writer">
			${comment.writer}
			<span class="comment-created-At">${comment.createdAt}</span>
		</div>
		<div class="comment-contents">
			${comment.contents}
		</div>
		<div class="comment-btn-area">
			<button>LIKE ${comment.good}</button>
			<button>DISLIKE ${comment.bad}</button>
			<button onclick="setReplyArea(${comment.id});">REPLY</button>
			<button onclick="deleteComment(${comment.id});">DELETE</button>
		</div>
		<div class="new-reply-area" id="new-reply-area-${comment.id}">
			<div class="new-reply-textarea" contenteditable="true" id="new-reply-contents-${comment.id}">

			</div>
			<div class="new-reply-btn">
				<button onclick="">ADD</button>
			</div>
		</div>
		<div class="replies-area">
			<c:forEach var="reply" items="${replies}">
			<div class="reply-in-replies">
				<div class="reply-writer">
					${reply.writer}
					<span class="reply-created-at">${reply.createdAt}</span>
				</div>
				<div class="reply-contents">
					${reply.contents}
				</div>
				<div class="reply-btn-area">
					<button>LIKE ${reply.good}</button>
					<button>DISLIKE ${reply.bad}</button>
					<button onclick="">DELETE</button>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
	</c:forEach>
	<div class="comments-pagination">
		<ul>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(1)"><<</a></li>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(${previousPage})"><</a></li>
			<c:forEach var="i" begin="${startNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
				<c:choose>
					<c:when test="${page == i}">
						<li class="on">${i}</li>
					</c:when>
					<c:otherwise>
					<li><a href="javascript:return fail;" onclick="getCommentsByPage(${previousPage})">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(${nextPage})">></a></li>
			<li><a href="javascript:return fail;)" onclick="getCommentsByPage(${totalPages})">>></a></li>
		</ul>
	</div>
</div>
</body>
</html>
