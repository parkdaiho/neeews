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

function pollComment(id, flag, page, order) {
    let body = JSON.stringify({
        "domain": "comments",
        "id": id,
        "flag": flag,
    });

    function success() {
        getCommentPage(page, order);
    }

    function fail() {
        alert("poll fail");
        console.log("id: " + id);
        console.log("flag: " + flag);
        console.log("page: " + page);
        console.log("order: " + order);
    }

    apiRequest("/api/poll", Method.PUT, body, getHeaders(true), success, fail);
}