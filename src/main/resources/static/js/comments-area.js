let clicked = false;
function addComment() {
    if(clicked) return false;
    clicked = true;

    let text = document.getElementById("new-comment-text");
    if(text.innerHTML.trim() === "") {
        alert(blankTextMessage);

        clicked = false;

        return;
    }

    let body = JSON.stringify({
        "id": id,
        "domain": domain,
        "contents" : text.innerHTML,
    });

    function success() {
        alert("success.");

        location.reload();
    }

    function fail() {
        alert("fail to add comment.");
        console.log("id: " + id);
        console.log("domain: " + domain);
    }

    apiRequest("/api/comment", Method.POST, body, getHeaders(true), success, fail);
}

function getCommentPage(page, order) {
    let params = new URLSearchParams({
        "page": page,
        "order": order,
    });
    let url = "/" + domain + "/" + id + "/comments?" + params;

    fetch(url)
        .then(response => {
            return response.text();
        })
        .then(result => {
            let commentsArea = document.getElementById("comments-area");
            commentsArea.innerHTML = result;
        });
}

function getCommentsByPage(page) {
    let order = document.getElementById("order").value;
    getCommentPage(page, order);
}

function addReply(parentCommentId, page, order) {
    if (clicked) return false;
    clicked = true;

    let text = document.getElementById("new-reply-contents-" + parentCommentId);
    if(text.innerHTML.trim() === "") {
        alert(blankTextMessage);

        clicked = false;

        return;
    }

    let body = JSON.stringify({
        "parentCommentId": parentCommentId,
        "contents": text.innerHTML,
    });

    function success() {
        getCommentPage(page, order);
    }

    function fail() {
        alert("fail to add reply.");
        console.log("parentCommentId: " + parentCommentId);
    }

    apiRequest("/api/reply", Method.POST, body, getHeaders(true), success, fail);
}

function setNewReplyArea(commentId) {
    let newReplyArea = document.getElementById("new-reply-area-" + commentId);

    if(newReplyArea.style.display === "none") {
        newReplyArea.style.display = "";
    } else {
        newReplyArea.style.display = "none";
    }
}

function setRepliesArea(commentId, repliesSize) {
    let repliesArea = document.getElementById("replies-area-" + commentId);
    let reliesAreaBtn = document.getElementById("replies-area-btn-" + commentId);

    if(repliesArea.style.display === "none") {
        reliesAreaBtn.innerHTML = "답글 " + repliesSize + "개 ▲";
        repliesArea.style.display = "";
    } else {
        reliesAreaBtn.innerHTML = "답글 " + repliesSize + "개 ▼"
        repliesArea.style.display = "none";
    }
}

function getCommentsByOrder(order) {
    getCommentPage(1, order);
}

function deleteComment(commentId, page, order) {
    if (clicked) return false;
    clicked = true;

    let params = new URLSearchParams({
        "id": commentId,
    });
    let url = "/api/comment?" + params;

    function success() {
        getCommentPage(page, order);
    }

    function fail() {
        alert("fail to delete comment");
    }

    apiRequest(url, Method.DELETE, null, getHeaders(true), success, fail);
}