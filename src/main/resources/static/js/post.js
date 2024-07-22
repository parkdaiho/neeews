let clicked = false;

function getModifyPost(postId) {
    let params = new URLSearchParams({
        "id": postId
    });
    let url = "/post?" + params;

    location.replace(url);
}

function deletePost(postId) {
    if(clicked) return false;
    clicked = true;

    let url = "/api/posts/" + postId;

    function success() {
        alert("success!");
        location.replace("/posts");
    }

    function fail() {
        alert("fail");
    }

    apiRequest(url, Method.DELETE, null, getHeaders(false), success, fail);
}