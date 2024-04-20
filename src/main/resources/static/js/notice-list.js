function getNoticeByOrder(order) {
    let params = new URLSearchParams({
        "order": order,
    });
    let url = "notice-list?" + params;

    location.replace(url);
}

function getPages(page, order, query) {
    let params = new URLSearchParams({
        "page": page,
        "order": order,
        "searchSort": document.getElementById("search-sort").value,
        "query": query
    });
    let url = "/notice-list?" + params;

    location.replace(url);
}