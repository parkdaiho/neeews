const commentsArea = document.getElementById("comments-area");

function addComment() {
    let body = JSON.stringify({
        "id": id,
        "domain": domain,
        "contents" : document.getElementById("comment-create-contents").value,
    });

    fetch("/api/comment", {
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
        });
}

function getCommentPage(page, sort) {
    let url = "/" + domain + "/" + id + "/comments?page=" + page + "&sort=" + sort;

    fetch(url)
        .then(response => {
            return response.text();
        })
        .then(result => {
            commentsArea.innerHTML = result;
        });
}

function addReply(parentCommentId, page, sort) {
    let body = JSON.stringify({
        "parentCommentId": parentCommentId,
        "contents": document.getElementById("reply-contents-" + parentCommentId).value,
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
                getCommentPage(page, sort)
            }
        });
}

function setReplyArea(id) {
    let replyArea = document.getElementById("reply-area-" + id);

    if(replyArea.style.display === "none") {
        replyArea.style.display = "";
    } else {
        replyArea.style.display = "none";
    }
}