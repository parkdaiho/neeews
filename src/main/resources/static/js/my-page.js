const area = document.getElementById("my-page-area");

function getMemberInfoPage() {
    let url = "/my-page/information";

    getPageInMyPage(url, "MEMBER-INFO");

    let memberInfoMenu = document.getElementById("member-info-menu");
    menuOn(memberInfoMenu);
}

function getWithdrawalPage(userid) {
    let url = "/my-page/withdrawal/" + userid;

    getPageInMyPage(url, "WITHDRAWAL");

    let withdrawalMenu = document.getElementById("withdrawal-menu");
    menuOn(withdrawalMenu);
}

function getMembershipPage(page, sort, searchSort, query) {
    let params = new URLSearchParams({
        "page": page,
        "sort": sort,
        "searchSort": searchSort,
        "query": query
    });
    let url = "/my-page/membership?" + params;

    getPageInMyPage(url, "MEMBERSHIP");

    let membershipMenu = document.getElementById("membership-menu");
    menuOn(membershipMenu);
}

function getMyPostsPage(page) {
    let param = new URLSearchParams({
        "page": page,
    });
    let url = "/my-page/my-posts?" + param;

    getPageInMyPage(url, "MY-POSTS");

    let myPostsMenu = document.getElementById("my-posts-menu");
    menuOn(myPostsMenu);
}

function getMyCommentsPage(page) {
    let param = new URLSearchParams({
        "page": page,
    });
    let url = "/my-page/my-comments?" + param;

    getPageInMyPage(url, "MY-COMMENTS");

    let myCommentsMenu = document.getElementById("my-comments-menu");
    menuOn(myCommentsMenu);
}

function getPageInMyPage(url, pageName) {
    fetch(url, {
        method: Method.GET,
        headers: getHeaders(false),
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                alert("Loading " + pageName + " page failed.");
            }
        })
        .then(result => {
            area.innerHTML = result;
        })
}

function menuOn(menuElement){
    let menuOnElement = document.getElementsByClassName("menu-on");
    menuOnElement.item(0).setAttribute("class", "");

    menuElement.setAttribute("class", "menu-on")
}