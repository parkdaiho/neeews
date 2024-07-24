const usernameValidFlag = document.getElementById("username-valid-flag");
const nicknameValidFlag = document.getElementById("nickname-valid-flag");
const confirmPasswordValidFlag = document.getElementById("confirm-password-valid-flag");
const emailValidFlag =  document.getElementById("email-valid-flag");
const emailAuthFlag = document.getElementById("email-auth-flag");

const username = document.getElementById("username");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");
const nickname = document.getElementById("nickname");
const email = document.getElementById("email");
const provider = document.getElementById("provider");

const usernameValidCheckMessage = document.getElementById("username-valid-check-message");
const passwordValidCheckMessage = document.getElementById("password-valid-check-message");
const nicknameValidCheckMessage = document.getElementById("nickname-valid-check-message");
const emailValidCheckMessage = document.getElementById("email-valid-check-message");

const emailAuthArea = document.getElementById("email-auth-area");

const validUsernameMessage = "사용가능한 아이디입니다.";
const validEmailMessage = "이메일 인증이 완료되었습니다.";

const usernameDupFailMessage = "이미 사용중인 아이디입니다.";
const emailDupFailMessage = "이미 사용중인 이메일입니다.";
const emailAuthFailMessage = "인증번호를 확인해주세요.";

const uncheckedEmailAuthMessage = "이메일을 인증해주세요.";

function signUp() {
    if(!checkFlag()) return;

    let body = JSON.stringify({
        username: username.value,
        password: password.value,
        nickname: nickname.value,
        email: email.value,
        provider: provider.value
    });

    fetch("/sign-up", {
        method: "POST",
        headers: {
            "Content-type": "application/json",
        },
        body: body
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then(result => {
            let params = new URLSearchParams(result);

            login(params);
        });
}

function login(params) {
    let form = document.createElement("form");
    form.setAttribute("method", Method.POST);
    form.setAttribute("action", "/login?" + params);

    document.body.appendChild(form);
    form.submit();
}

function checkFlag() {
    if(usernameValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedUsernameMessage);

        return false;
    }

    if(confirmPasswordValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedPasswordMessage)
        password.value = "";

        return false;
    }

    if(nicknameValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedNicknameMessage);

        return false;
    }

    if(emailValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedEmailMessage);

        return false;
    }

    if(emailAuthFlag.value === Check.UNCHECKED) {
        alert(uncheckedEmailAuthMessage);

        return false;
    }

    return true;
}

function changeUsername() {
    usernameValidFlag.value = Check.UNCHECKED;

    usernameValidCheckMessage.innerHTML = "";
    usernameValidCheckMessage.style.display = "none";
}

function changePassword() {
    if(!passwordRegex.test(password.value)) {
        showValidCheckMessage(passwordValidCheckMessage, passwordRegexFailMessage);

        return;
    }

    if(password.value !== confirmPassword.value) {
        confirmPasswordValidFlag.value = Check.UNCHECKED;
        showValidCheckMessage(passwordValidCheckMessage, passwordConfirmFailMessage);
    } else {
        confirmPasswordValidFlag.value = Check.CHECKED;
        showValidCheckMessage(passwordValidCheckMessage, validPasswordMessage);
    }
}

function changeNickname() {
    nicknameValidFlag.value = Check.UNCHECKED;

    nicknameValidCheckMessage.innerHTML = "";
    nicknameValidCheckMessage.style.display = "none";
}

function usernameValidCheck() {
    if(!usernameRegex.test(username.value)) {
        alert(usernameRegexFailMessage);
        showValidCheckMessage(usernameValidCheckMessage, usernameRegexFailMessage);

        return;
    }

    let body = JSON.stringify({
        "username": username.value,
    });

    function success() {
        alert(validUsernameMessage)

        usernameValidFlag.value = Check.CHECKED;
        showValidCheckMessage(usernameValidCheckMessage, validUsernameMessage)
    }

    function fail() {
        alert(usernameDupFailMessage);
    }

    let url = "/sign-up/username";
    validCheckRequest(url, body, success, fail);
}

function nicknameValidCheck() {
    if(!nicknameRegex.test(nickname.value)) {
        alert(nicknameRegexFailMessage);
        showValidCheckMessage(nicknameValidCheckMessage, nicknameRegexFailMessage);

        return;
    }

    let body = JSON.stringify({
        "nickname": nickname.value,
    });

    function success() {
        alert(validNicknameMessage);

        nicknameValidFlag.value = Check.CHECKED;
        showValidCheckMessage(nicknameValidCheckMessage, validNicknameMessage);
    }

    function fail() {
        alert(nicknameDupFailMessage);
    }

    let url = "/sign-up/nickname";
    validCheckRequest(url, body, success, fail);
}

function emailValidCheck() {
    let body = JSON.stringify({
        "email": email.value,
    });

    function success() {
        emailValidFlag.value = Check.CHECKED;

        email.setAttribute("readonly", "readonly");
        emailAuthArea.style.display = "";
    }

    function fail() {
        alert(emailDupFailMessage);
    }

    let url = "/sign-up/email";
    validCheckRequest(url, body, success, fail);
}

function emailAuthCheck() {
    let emailAuthNumber = document.getElementById("email-auth-number");
    let body = JSON.stringify({
        "email": email.value,
        "emailAuthNumber": emailAuthNumber.value,
    });

    function success() {
        alert(validEmailMessage);

        emailAuthArea.style.display = "none";
        showValidCheckMessage(emailValidCheckMessage, validEmailMessage);
    }

    function fail() {
        alert(emailAuthFailMessage);

        emailAuthNumber.value = "";
    }

    let url = "/sign-up/email-auth"
    validCheckRequest(url, body, success, fail)
}

function validCheckRequest(url, body, success, fail) {
    fetch(url, {
        method: Method.POST,
        headers: getHeaders(true),
        body: body,
    })
        .then(response => {
            if(response.ok) {
                return response.json();
            }
        })
        .then(result => {
            if(result) {
                success();
            } else {
                fail();
            }
        })
}

function showValidCheckMessage(object, message) {
    object.style.display = "";
    object.innerHTML = message;
}