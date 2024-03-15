const commentCreateButton = document.getElementById("comment-create-button");
const commentsArea = document.getElementById("comments-area");

if(commentCreateButton) {
    commentCreateButton.addEventListener("click", () => {
        let body = JSON.stringify({
            "articleId" : articleId,
            "contents" : document.getElementById("comment-create-contents").value,
        });

        fetch("/api/article-comment", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("access_token"),
                "Content-type": "application/json",
            },
            body: body,
        })
            .then(response => {
                if(response.ok) {
                    alert("댓글 작성을 완료했습니다.");
                    location.reload();
                }
            })
    });
}

function getArticlePage(page, sort) {
    let url = "/articles/" + articleId + "/comments?page=" + page + "&sort=" + sort;

    getPage(url);
}

function getPage(url) {
    fetch(url)
        .then(response => {
            return response.text();
        })
        .then(result => {
            commentsArea.innerHTML = result;
        });
}

function setGoodOrBad(commentId, flag, page, sort) {
    let body = JSON.stringify({
        "commentId": commentId,
        "flag": flag,
    });

    fetch("/api/article-comment/recommendation", {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("access_token"),
            "Content-type": "application/json",
        },
        body: body,
    })
        .then(response => {
            if(response.ok) {
                getArticlePage(page, sort);
            }
        });
}

function addReply(parentCommentId, page, sort) {
    let contents = document.getElementById("reply_contents");

    let body = JSON.stringify({
        "parentCommentId": parentCommentId,
        "contents": contents,
    });

    fetch("/api/reply", {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("access_token"),
            "Content-type": "application/json",
        },
        body: body,
    })
        .then(response => {
            if(response.ok) {
                getArticlePage(page, sort)
            }
        });
}

function setReplyArea(id, page, sort) {
    let commentArea = document.getElementById("comment_" + id);
    let replyArea = document.getElementsByClassName("reply_area");

    if(replyArea) {
        replyArea.remove();
    }

    replyArea = document.createElement("div");
    replyArea.setAttribute("class", "reply");

    let replyContents = document.createElement("textarea");
    replyContents.setAttribute("id", "reply_contents");

    let addReplyButton = document.createElement("button");
    addReplyButton.setAttribute("class", "reply_button");
    addReplyButton.addEventListener("click", () => addReply(id, page, sort));

    commentArea.append()
}