function getFindUsernamePage() {
    let url = "/find-username";

    getPage(url, "find-username");
}

function getFindPasswordPage() {
    let url = "/find-password";

    getPage(url, "find-password");
}

function getFoundUsername(username) {
    let params = new URLSearchParams({
        "username": username,
    });
    let url = "/found-username?" + params;

    getPage(url, "found-username");
}

function getLoginPage() {
    let url = "/login";
    location.replace(url);
}

function getPage(url, pageName) {
    let findUserInfoPage = document.getElementById("find-user-info-page");
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
            findUserInfoPage.innerHTML = result;
        });
}