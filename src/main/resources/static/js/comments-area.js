function addComment() {
    let body = JSON.stringify({
        "id": id,
        "domain": domain,
        "contents" : document.getElementById("comment-create-contents").innerHTML,
    });

    function success() {
        alert("success.");

        location.reload();
    }

    function fail() {
        alert("fail");
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
    let body = JSON.stringify({
        "parentCommentId": parentCommentId,
        "contents": document.getElementById("new-reply-contents-" + parentCommentId).innerHTML,
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
                getCommentPage(page, order)
            }
        });
}

function setReplyArea(commentId) {
    let newReplyArea = document.getElementById("new-reply-area-" + commentId);

    if(newReplyArea.style.display === "none") {
        newReplyArea.style.display = "";
    } else {
        newReplyArea.style.display = "none";
    }
}

function getCommentsByOrder(order) {
    getCommentPage(1, order);
}