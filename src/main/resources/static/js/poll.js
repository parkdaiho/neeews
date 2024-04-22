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