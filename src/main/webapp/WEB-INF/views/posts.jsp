<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<div class="post-list-info">
	총 10 개의 게시물
	<select>
		<option>최신순</option>
		<option>조회수순</option>
		<option>내가 쓴 게시물</option>
	</select>
</div>
	<div class="post-list-wrap">
		<ul>
			<li class="post-list-seq">번호</li>
			<li class="post-list-title">제목</li>
			<li class="post-list-writer">글쓴이</li>
			<li class="post-list-created-date">작성날짜</li>
		</ul>
		<div class="post-in-list">
			<ul>
				<li class="post-seq">1</li>
				<li class="post-title">제목이요</li>
				<li class="post-writer">송상두</li>
				<li class="post-created-date">2024-03-03</li>
			</ul>
		</div>
		<div class="post-list-write">
			<a href="/new-post">WRITE</a>
		</div>
		<div class="post-list-pagination">
			<ul>
				<li><button><<</button></li>
				<li><button><</button></li>
				<li><button>1</button></li>
				<li><button>></button></li>
				<li><button>>></button></li>
			</ul>
		</div>
	</div>
	<div class="post-list-extra">
		<div class="post-list-search-area">
			<div class="post-list-search-box">
				<div class="search-box">
					<select name="" id="">
						<option value="date" selected>제목 + 내용</option>
						<option value="sim">글쓴이</option>
					</select>
					<input type="text" id="query" placeholder="Search Post">
					<button type="button">검색</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
