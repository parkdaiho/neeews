function getFindUsernamePage() {
    let url = "/find-username";

    getPage(url, "find-username");
}

function getFindPasswordPage() {
    let url = "/find-password";

    getPage(url, "find-password");
}

function getFindUsernameResultPage() {
    let url = "/find-username/result";

    getPage(url, "find-username-result");
}

function getFindPasswordResultPage() {
    let url = "/find-password/result";

    getPage(url);
}

function getChangePasswordPage() {
    let url = "/change-password";

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

function loading() {
    let loading = document.getElementById("find-user-info-loading");
    let loadingDisplay = loading.style.display;

    if(loadingDisplay === "none") {
        loading.style.display = "";
    } else {
        loading.style.display = "none";
    }
}