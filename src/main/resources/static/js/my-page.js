const area = document.getElementById("my-page-area");

function getMemberInfoPage() {
    let url = "/user-information";

    getPageInMyPage(url, "MEMBER-INFO");
}

function getWithdrawalPage() {
    let url = "/withdrawal";

    getPageInMyPage(url, "WITHDRAWAL");
}

function getMembershipPage() {
    let url = "/membership"

    getPageInMyPage(url, "MEMBERSHIP");
}

function getPageInMyPage(url, pageName) {
    fetch(url, {
        method: Method.GET,
        headers: getHeaders(false),
    })
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
}