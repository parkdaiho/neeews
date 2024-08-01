function sendCodeForPassword() {
    let email = document.getElementById("email");
    let username = document.getElementById("username");
    let body = JSON.stringify({
        "email": email.value,
        "username": username.value,
    });

    let url = "/api/password";

    function success() {
        getPage(url, "change-password");
    }

    function fail() {
        alert("Fail api-request");
    }

    apiRequestInFindPassword(url, body, success, fail);
}

function authCodeForPassword() {
    let email = document.getElementById("email");
    let code = document.getElementById("code");
    let body = JSON.stringify({
        "email": email.value,
        "code":code.value,
    });

    let url = "/api/password/authentication";
    function success() {
        getPage(url, "authCode")
    }
}

function apiRequestInFindPassword(url, body, success, fail) {
    fetch(url, {
        method: Method.POST,
        headers: getHeaders(true),
        body: body,
    })
        .then(response => {
            if(response.ok) {
                return success();
            } else {
                return fail();
            }
        })
}