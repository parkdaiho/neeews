function clippingArticle() {
    clickClippingBtn(Method.POST);
}

function cancelClipping() {
    clickClippingBtn(Method.DELETE);
}

function clickClippingBtn(method) {
    let body = JSON.stringify({
        "id": id,
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