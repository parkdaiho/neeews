function sendCodeForPassword() {
    let email = document.getElementById("email");
    let username = document.getElementById("username");
    let body = JSON.stringify({
        "username": username.value,
        "email": email.value,
    });

    function success() {
        let codeArea = document.getElementById("find-user-info-code");
        codeArea.style.display = "";

        let sendBtn = document.getElementById("find-password-send-btn");
        sendBtn.style.display = "none";

        let authBtn = document.getElementById("find-password-auth-btn");
        authBtn.style.display = "";
    }

    function fail() {
        alert("Fail api-request");
    }

    let url = "/api/password";
    apiRequestInFindPassword(url, body, success, fail);
}

function authCodeForPassword() {
    let email = document.getElementById("email");
    let code = document.getElementById("code");
    let body = JSON.stringify({
        "email": email.value,
        "code": code.value,
    });

    function success() {
        getChangePasswordPage();
    }

    function fail() {
        alert("Fail api-request");
    }

    let url = "/api/password/authentication";
    apiRequestInFindPassword(url, body, success, fail);
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

function checkRegex() {
    let regexFlag = document.getElementById("regex-check");
    regexFlag.value = Check.UNCHECKED;

    let confirmFlag = document.getElementById("confirm-check");
    confirmFlag.value = Check.UNCHECKED;

    let validCheckMessageArea= document.getElementById("change-password-valid-check-message");
    validCheckMessageArea.style.display = "";

    let password = document.getElementById("password");
    if(!passwordRegex.test(password.value)) {
        validCheckMessageArea.innerHTML = passwordRegexFailMessage;
        regexFlag.value = Check.UNCHECKED;

        return;
    }

    validCheckMessageArea.innerHTML = validPasswordMessage;
    regexFlag.value = Check.CHECKED;
}

function checkConfirm() {
    let confirmFlag = document.getElementById("confirm-check");
    confirmFlag.value = Check.UNCHECKED;

    let regexFlag = document.getElementById("regex-check");
    if(regexFlag.value === Check.UNCHECKED) return;

    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirm-password").value;

    if(password === confirmPassword) {
        let validCheckMessageArea= document.getElementById("change-password-valid-check-message");
        validCheckMessageArea.style.display = "none";

        confirmFlag.value = Check.CHECKED;
    }
}

function changePassword() {
    let regexFlag = document.getElementById("regex-check");
    if (regexFlag === Check.UNCHECKED) {
        alert(passwordRegexFailMessage);

        return;
    }

    let confirmFlag = document.getElementById("confirm-check");
    if(confirmFlag === Check.UNCHECKED) {
        alert(passwordConfirmFailMessage);

        return;
    }

    let confirmPassword = document.getElementById("confirm-password");
    let body = JSON.stringify({
        "password": confirmPassword.value,
    });

    let url = "/api/password"
    fetch(url, {
        method: Method.PUT,
        headers: getHeaders(true),
        body: body,
    })
        .then(response => {
            if(response.ok) {
                getFindPasswordResultPage();
            } else {
                alert("fail to change password");
            }
        });
}