function getNoticeByOrder(order) {
    let params = new URLSearchParams({
        "order": order,
    });
    let url = "notice-list?" + params;

    location.replace(url);
}

function getPages(page, order, searchSort, query) {
    let params = new URLSearchParams({
        "page": page,
        "order": order,
        "searchSort": searchSort,
        "query": query
    });
    let url = "/notice-list?" + params;

    location.replace(url);
}