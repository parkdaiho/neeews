const area = document.getElementById("my-page-area");

function getMemberInfoPage() {
    let url = "/information";

    getPageInMyPage(url, "MEMBER-INFO");
}

function getWithdrawalPage() {
    let url = "/withdrawal";

    getPageInMyPage(url, "WITHDRAWAL");
}

function getMembershipPage() {
    let url = "/membership";

    getPageInMyPage(url, "MEMBERSHIP");
}

function getPageInMyPage(url, pageName) {
    fetch(url)
        .then(response => {
            if(response.ok) {
                area.innerHTML = response.text();
            } else {
                alert("Loading " + pageName + " page failed.");
            }
        });
}