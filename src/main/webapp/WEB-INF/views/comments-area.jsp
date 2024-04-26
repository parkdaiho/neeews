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
	<span class="total-comments">TOTAL ${totalElements} COMMENTS</span>
	<select onchange="getCommentsByOrder(this.value);" id="order">
		<c:choose>
			<c:when test="${order == 'earliest'}">
				<option value="latest">LATEST</option>
				<option value="earliest" selected>EARLIEST</option>
				<option value="popular">MOST-POPULAR</option>
				<option value="comments">MOST-REPLIES</option>
			</c:when>
			<c:when test="${order == 'popular'}">
				<option value="latest">LATEST</option>
				<option value="earliest">EARLIEST</option>
				<option value="popular" selected>MOST-POPULAR</option>
				<option value="comments">MOST-REPLIES</option>
			</c:when>
			<c:when test="${order == 'comments'}">
				<option value="latest">LATEST</option>
				<option value="earliest">EARLIEST</option>
				<option value="popular">MOST-POPULAR</option>
				<option value="comments" selected>MOST-REPLIES</option>
			</c:when>
			<c:otherwise>
				<option value="latest" selected>LATEST</option>
				<option value="earliest">EARLIEST</option>
				<option value="popular">MOST-POPULAR</option>
				<option value="comments">MOST-REPLIES</option>
			</c:otherwise>
		</c:choose>
	</select>
</div>
<div class="comment-list">
	<c:forEach var="comment" items="${comments}">
		<div class="comment-in-comments">
			<div class="comment-writer">
					${comment.writer}
				<span class="comment-created-At">${comment.createdAt}</span>
			</div>
			<div class="comment-contents">
					${comment.contents}
			</div>
			<div class="comment-btn-area">
				<button onclick="pollComment(${comment.id}, true, ${page}, '${order}')">LIKE ${comment.good}</button>
				<button onclick="pollComment(${comment.id}, false, ${page}, '${order}')">DISLIKE ${comment.bad}</button>
				<button onclick="setReplyArea(${comment.id});">REPLY</button>
				<button onclick="deleteComment(${comment.id}, ${page}, '${order}');">DELETE</button>
			</div>
			<div class="new-reply-area" id="new-reply-area-${comment.id}" style="display: none;">
				<div class="new-reply-textarea" contenteditable="true" id="new-reply-contents-${comment.id}">

				</div>
				<div class="new-reply-btn">
					<button onclick="addReply(${comment.id}, ${page}, '${order}')">ADD</button>
					<button onclick="setReplyArea(${comment.id});">CLOSE</button>
				</div>
			</div>
			<div class="replies-area">
				<c:forEach var="reply" items="${comment.replies}">
					<div class="reply-in-replies">
						<div class="reply-writer">
								${reply.writer}
							<span class="reply-created-at">${reply.createdAt}</span>
						</div>
						<div class="reply-contents">
								${reply.contents}
						</div>
						<div class="reply-btn-area">
							<button onclick="pollComment(${reply.id}, true, ${page}, '${order}')">LIKE ${reply.good}</button>
							<button onclick="pollComment(${reply.id}, false, ${page}, '${order}')">DISLIKE ${reply.bad}</button>
							<button onclick="">DELETE</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
	<c:if test="${totalElements != 0}">
	<div class="comments-pagination">
		<ul>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(1);"><<</a></li>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(${previousPage});"><</a></li>
			<c:forEach var="i" begin="${startNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
				<c:choose>
					<c:when test="${page == i}">
						<li class="on">${i}</li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:return fail;" onclick="getCommentsByPage(${previousPage});">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a href="javascript:return fail;" onclick="getCommentsByPage(${nextPage});">></a></li>
			<li><a href="javascript:return fail;)" onclick="getCommentsByPage(${totalPages});">>></a></li>
		</ul>
	</div>
	</c:if>
</div>
</body>
</html>
