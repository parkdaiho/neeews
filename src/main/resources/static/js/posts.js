function getPostsByOrder(order) {
    let params = new URLSearchParams({
        "order": order
    });

    let url = "/posts?" + params;
    location.replace(url);
}

function getPostView(postId) {
    let url = "/posts/" + postId;
    location.replace(url);
}