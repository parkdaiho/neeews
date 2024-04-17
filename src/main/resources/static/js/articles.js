function getArticles(order) {
    let params = new URLSearchParams({
        "order": order
    });

    let url = "/articles?" + params;

    location.replace(url);
}
