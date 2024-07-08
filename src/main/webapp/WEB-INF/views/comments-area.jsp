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
	<div class="total-comments">
		TOTAL <b>${totalElements}</b> COMMENTS
	</div>
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
			<div class="comment-info">
				<div class="comment-writer">${comment.writer}</div>
				<div class="comment-created-At">${comment.createdAt}</div>
			</div>
			<div class="comment-contents">
					${comment.contents}
			</div>
			<div class="comment-btn-area">
				<button onclick="pollComment(${comment.id}, true, ${page}, '${order}')"><span>좋아요</span>${comment.good}</button>
				<button onclick="pollComment(${comment.id}, false, ${page}, '${order}')"><span>싫어요</span>${comment.bad}</button>
				<button onclick="setNewReplyArea(${comment.id});">REPLY</button>
				<c:if test="${comment.writer == nickname}">
					<button onclick="deleteComment(${comment.id}, ${page}, '${order}');">DELETE</button>
				</c:if>
			</div>
			<c:if test="${comment.replies.size() != 0}">
			<button onclick="setRepliesArea(${comment.id}, ${comment.replies.size()});"
							class="replies-area-btn"
							id="replies-area-btn-${comment.id}">답글 ${comment.replies.size()}개 ▼</button>
			</c:if>
			<div class="new-reply-area" id="new-reply-area-${comment.id}" style="display: none;">
				<div class="new-reply-textarea">
					<div class="new-reply-text" contenteditable="true" id="new-reply-contents-${comment.id}"></div>
				</div>
				<div class="new-reply-btn-area">
					<button class="add" onclick="addReply(${comment.id}, ${page}, '${order}')">ADD</button>
					<button onclick="setNewReplyArea(${comment.id});">CLOSE</button>
				</div>
			</div>
			<div class="replies-area" id="replies-area-${comment.id}" style="display: none;">
				<c:forEach var="reply" items="${comment.replies}">
					<div class="reply-in-replies">
						<div class="reply-info">
							<div class="reply-writer">${reply.writer}</div>
							<div class="reply-created-at">${reply.createdAt}</div>
						</div>
						<div class="reply-contents">
								${reply.contents}
						</div>
						<div class="reply-btn-area">
							<button onclick="pollComment(${reply.id}, true, ${page}, '${order}')"><span>좋아요</span>${reply.good}</button>
							<button onclick="pollComment(${reply.id}, false, ${page}, '${order}')"><span>싫어요</span>${reply.bad}</button>
							<c:if test="${reply.writer == nickname || isManager}">
								<button onclick="">DELETE</button>
							</c:if>
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
						<li class="selected-comments-page">${i}</li>
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
