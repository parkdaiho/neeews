function getPostsByOrder(order) {
    let searchSort = document.getElementById("search-sort");
    let query = document.getElementById("query");

    let params = new URLSearchParams({
        "order": order,
        "searchSort": searchSort.value,
        "query": query.value
    });
    let url = "/posts?" + params;

    location.replace(url);
}

function getPostView(postId) {
    let url = "/posts/" + postId;

    location.replace(url);
}

function getPages(page, order, searchSort, query) {
    let params = new URLSearchParams({
        "page": page,
        "order": order,
        "searchSort": searchSort,
        "query": query
    });
    let url = "/posts?" + params;

    location.replace(url);
}