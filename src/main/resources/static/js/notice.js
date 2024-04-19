function getModifyNotice(noticeId) {
    let params = new URLSearchParams({
        "id": noticeId
    });
    let url = "/notice?" + params;

    location.replace(url);
}

function deleteNotice(noticeId) {
    let url = "/api/notice/" + noticeId;

    function success() {
        alert("success");
        location.replace("/notice-list");
    }

    function fail() {
        alert("fail");
    }

    apiRequest(url, Method.DELETE, null, getHeaders(true), success, fail);
}