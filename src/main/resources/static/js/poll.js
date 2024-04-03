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
    function success() {
        alert("poll success")
        location.reload();
    }

    function fail() {
        alert("poll fail")
    }

    pollRequest(id, flag, domain, success, fail);
}

function pollRequest(id, flag, domain, success, fail) {
    let url = "/api/poll";
    let headers = {
        "Authorization": "Bearer " + localStorage.getItem("access_token"),
        "Content-type": "application/json",
    };
    let body = JSON.stringify({
        "id": id,
        "domain": domain,
        "flag": flag,
    });

    apiRequest(url, Method.PUT, headers, body, success, fail);
}