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
	<c:if test="${sort eq 'date'}">
		<button id="sort-by-date" class="on" onclick="getCommentPage(1, 'date')">최신순</button>
		<button id="sort-by-good" onclick="getCommentPage(1, 'good')">인기순</button>
	</c:if>
	<c:if test="${sort eq 'good'}">
		<button id="sort-by-date" onclick="getCommentPage(1, 'date')">최신순</button>
		<button id="sort-by-good" class="on" onclick="getCommentPage(1, 'good')">인기순</button>
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
				<button onclick="setGoodOrBad(${comment.id}, '${true}', ${page}, '${sort}')">👍${comment.good}</button>
				<button onclick="setGoodOrBad(${comment.id}, '${false}', ${page}, '${sort}')">👎${comment.bad}</button>
			</div>
		</div>
		<div class="reply-area" id="reply-area-${comment.id}" style="display: none;">
			<textarea id="reply-contents-${comment.id}"></textarea>
			<button onclick="addReply(${comment.id}, ${page}, '${sort}');">답변하기</button>
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
			<button id="load-first-page-of-comments" onclick="getCommentPage(1, '${sort}')"><<</button>
		</li>
		<li>
			<button id="load-previous-page-of-comments" onclick="getCommentPage(${previousPage}, '${sort}')"><</button>
		</li>
		<c:forEach var="i" begin="${firstNumOfPageBlock}" end="${lastNumOfPageBlock}" step="1">
			<li>
				<button class="page-btn" onclick="getCommentPage(${i}, '${sort}')">${i}</button>
			</li>
		</c:forEach>
		<li>
			<button id="load-next-page-of-comments" onclick="getCommentPage(${nextPage}, '${sort}')">></button>
		</li>
		<li>
			<button id="load-last-page-of-comments" onclick="getCommentPage(${totalPages}, '${sort}')">>></button>
		</li>
	</ul>
</div>
<script src="/js/comment.js"></script>
</body>
</html>
