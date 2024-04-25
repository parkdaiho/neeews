function getArticlesByOrder(order) {
    let params = new URLSearchParams({
        "order": order
    });

    let url = "/articles?" + params;

    location.replace(url);
}

function showArticle(id) {
    let url = "/articles/" + id;

    location.replace(url);
}