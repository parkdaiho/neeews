<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div>
	<textarea id="comment-create-contents">

	</textarea>
	<button id="comment-create-button" onclick="addComment();">댓글작성</button>
</div>
<div class="comments-info">
	<span>총 ${totalElements} 개의 댓글 </span>
	<c:if test="${order eq 'earliest'}">
		<button id="order-by-good" onclick="getCommentPage(1, 'earliest')">등록순</button>
		<button id="order-by-date" onclick="getCommentPage(1, 'latest')">최신순</button>
		<button id="order-by-good" onclick="getCommentPage(1, 'popularity')">인기순</button>
	</c:if>
	<c:if test="${order eq 'latest'}">
		<button id="order-by-good" onclick="getCommentPage(1, 'earliest')">등록순</button>
		<button id="order-by-date" onclick="getCommentPage(1, 'latest')">최신순</button>
		<button id="order-by-good" onclick="getCommentPage(1, 'popularity')">인기순</button>
	</c:if>
	<c:if test="${order eq 'popularity'}">
		<button id="order-by-good" class="on" onclick="getCommentPage(1, 'earliest')">등록순</button>
		<button id="order-by-date" onclick="getCommentPage(1, 'latest')">최신순</button>
		<button id="order-by-good" onclick="getCommentPage(1, 'popularity')">인기순</button>
	</c:if>
</div>
<div id="comments-view">
	<c:forEach var="comment" items="${comments}">
		<div id="comment_${comment.id}" onclick="setReplyArea(${comment.id});">
			<div>
				<span>${comment.writer}</span>
				<span>${comment.createdDate}</span>
			</div>
			<div>
					${comment.contents}
			</div>
			<div>
				<button onclick="commentPoll(${comment.id}, '${true}', ${page}, '${order}')">👍${comment.good}</button>
				<button onclick="commentPoll(${comment.id}, '${false}', ${page}, '${order}')">👎${comment.bad}</button>
			</div>
		</div>
		<div class="reply-area" id="reply-area-${comment.id}" style="display: none;">
			<textarea id="reply-contents-${comment.id}"></textarea>
			<button onclick="addReply(${comment.id}, ${page}, '${order}');">답변하기</button>
		</div>
		<c:forEach var="reply" items="${comment.reply}">
			<div class="reply-view">
				<div class="reply-writer">
						${reply.writer}
				</div>
				<div class="reply-contents">
						${reply.contents}
				</div>
				<div class="reply-createdDate">
						${reply.createdDate}
				</div>
			</div>
		</c:forEach>
	</c:forEach>
</div>
<div id="comments-page">
	<ul>
		<li>
			<button id="load-first-page-of-comments" onclick="getCommentPage(1, '${order}')"><<</button>
		</li>
		<li>
			<button id="load-previous-page-of-comments" onclick="getCommentPage(${previousPage}, '${order}')"><</button>
		</li>
		<c:forEach var="i" begin="${firstNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
			<li>
				<button class="page-btn" onclick="getCommentPage(${i}, '${order}')">${i}</button>
			</li>
		</c:forEach>
		<li>
			<button id="load-next-page-of-comments" onclick="getCommentPage(${nextPage}, '${order}')">></button>
		</li>
		<li>
			<button id="load-last-page-of-comments" onclick="getCommentPage(${totalPages}, '${order}')">>></button>
		</li>
	</ul>
</div>
<script src="/js/comment.js"></script>
<script src="/js/poll.js"></script>
</body>
</html>
