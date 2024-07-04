function showMyPost(postId) {
    let url = "/posts/" + postId;

    location.replace(url);
}

function getPages(page, sort, searchSort, query) {

    getMyPostsPage(page);
}