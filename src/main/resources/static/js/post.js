function getModifyPost(id) {
    let url = "/post?id=" + id;
    location.replace(url);
}

function deletePost(id) {
    let url = "/api/posts/" + id;

    function success() {
        alert("success!");
        location.replace("/posts");
    }

    function fail() {
        alert("fail");
    }

    apiRequest(url, Method.DELETE, null, getHeaders(false), success, fail);
}