function getNoticeByOrder(order) {
    let params = new URLSearchParams({
        "order": order,
        "searchSort": document.getElementById("search-sort").value,
        "query": document.getElementById("query").value,
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

function getNoticeView(noticeId) {
    let url = "/notice/" + noticeId;

    location.replace(url);
}