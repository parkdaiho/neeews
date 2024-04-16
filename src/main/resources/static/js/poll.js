function commentPoll(id, flag, page, order) {
    function success() {
        alert("poll success");
        getCommentPage(page, order);
    }

    function fail() {
        alert("poll fail");
    }

    pollRequest(id, flag, "comments", success, fail);
}

function poll(id, flag) {
    let url = "/api/poll";
    let body = JSON.stringify({
        "domain": domain,
        "id": id,
        "flag": flag,
    });

    function success() {
        alert("poll success")
        location.reload();
    }

    function fail() {
        alert("poll fail")
    }

    apiRequest(url, Method.PUT, body, getHeaders(true), success, fail);
}