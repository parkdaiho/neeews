function commentPoll(id, flag, page, sort) {
    function success() {
        alert("poll success");
        getCommentPage(page, sort);
    }

    function fail() {
        alert("poll fail");
    }

    pollRequest(id, flag, success, fail);
}

function poll(id, flag) {
    function success() {
        alert("poll success")
        location.reload();
    }

    function fail() {
        alert("poll fail")
    }

    pollRequest(id, flag, success, fail);
}

function pollRequest(id, flag, success, fail) {
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