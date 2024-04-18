function getPostsByOrder(order) {
    let params = new URLSearchParams({
        "order": order
    });

    let url = "/posts?" + params;
    location.replace(url);
}