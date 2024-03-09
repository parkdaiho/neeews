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

function setGoodOrBad(commentId, page, sort, flag) {
    let url = "/api/comments/" + commentId + "?page=" + page + "&sort=" + sort + "&flag=" + flag;

    fetch(url)
        .then(response => {
            return response.text();
        })
        .then(result => {
            commentArea.innerHTML = result;
        });
}