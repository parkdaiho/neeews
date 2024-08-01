function getFindUsernamePage() {
    let url = "/find-username";

    getPage(url, "find-username");
}

function getFindPasswordPage() {
    let url = "/find-password";

    getPage(url, "find-password");
}

function getFoundUsername() {
    let url = "/find-username/result";

    getPage(url, "find-username-result");
}

function getChangePasswordPage() {
    let url = "/find-password/result";

    getPage(url, "find-password-result");
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