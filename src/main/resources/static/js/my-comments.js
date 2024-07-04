function showMyComment(domain, parentId) {
    let url = "/" + domain + "/" + parentId;

    location.replace(url);
}

function getPages(page, sort, searchSort, query) {

    getMyCommentsPage(page);
}
