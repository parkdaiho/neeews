const area = document.getElementById("my-page-area");

function getMemberInfoPage() {
    let url = "/information";

    getPageInMyPage(url, "MEMBER-INFO");
}

function getWithdrawalPage() {
    let url = "/withdrawal";

    getPageInMyPage(url, "WITHDRAWAL");
}

function getMembershipPage(page, sort, query) {
    let url = "/membership?page=" + page + "&sort=" + sort + "&query=" + query;

    getPageInMyPage(url, "MEMBERSHIP");
}

function getPageInMyPage(url, pageName) {
    fetch(url)
        .then(response => {
            if(response.ok) {
                return response.text();
            } else {
                alert("Loading " + pageName + " page failed.");
            }
        })
        .then(result => {
            area.innerHTML = result;
        })
}

function getPage(page, sort, query) {
    getMembershipPage(page, sort, query);
}