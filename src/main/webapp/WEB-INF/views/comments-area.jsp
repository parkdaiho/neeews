<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>COMMENTS-AREA</title>
	<link rel="stylesheet" href="/css/css.css">
</head>
<body>
<div class="add-comment-area">
	<div class="add-comment-contents" contenteditable="true">

	</div>
	<button>ADD</button>
</div>
<div class="comments-info">
	<span class="total-comments">TOTAL 999</span>
	<select>
		<option value="latest">LATEST</option>
		<option value="earliest">EARLIEST</option>
		<option value="popular">MOST-POPULAR</option>
	</select>
</div>
<div class="comment-list">
	<div class="comment-in-comments">
		<div class="comment-writer">
			aaa
		</div>
		<div class="comment-created-at">
			2024.03.27
		</div>
		<div class="comment-contents">
			CONTENTS
		</div>
		<div class="comment-btn-area">
			<button>LIKE 999</button>
			<button>DISLIKE 999</button>
			<button>REPLY</button>
			<button>DELETE</button>
		</div>
		<div class="replies-area">
			<div class="reply-in-replies">
				<div class="reply-writer">
					bbb
					<span class="reply-pubdate">2024-03-28 15:19:07</span>
				</div>
				<div class="reply-contents">
					CONTENTS
				</div>
				<div class="reply-btn-area">
					<button>LIKE 999</button>
					<button>DISLIKE 999</button>
					<button>DELETE</button>
				</div>
			</div>
			...
		</div>
	</div>
	...
	<div class="comments-pagination">
		<ul>
			<li><a href=""><<</a></li>
			<li><a href=""><</a></li>
			<li><a href="">1</a></li>
			<li><a href="">2</a></li>
			<li><a href="">></a></li>
			<li><a href="">>></a></li>
		</ul>
	</div>
</div>
</body>
</html>
