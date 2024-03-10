const commentArea = document.getElementById("comments-area");

function getPage(page, sort) {
    let url = "/articles/" + articleId + "/comments?page=" + page + "&sort=" + sort;

    fetch(url)
        .then(response => {
            return response.text();
        })
        .then(result => {
            commentArea.innerHTML = result;
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
                getPage(page, sort);
            }
        });
}