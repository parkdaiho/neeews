function clippingArticle() {
    clickClippingBtn(Method.POST);
}

function cancelClipping() {
    clickClippingBtn(Method.DELETE);
}

function clickClippingBtn(method) {
    let body = JSON.stringify({
        "articleIdArr": [id],
    });

    function success() {
        alert("success");
        location.reload();
    }

    function fail() {
        alert("fail");
    }

    apiRequest("/api/clipping", method, body, getHeaders(true), success, fail);
}

function writePost() {
    let params = new URLSearchParams({
        "articleId": id,
    });

    let url = "/new-post?" + params;

    location.replace(url);
}