const usernameValidFlag = document.getElementById("username-valid-flag");
const nicknameValidFlag = document.getElementById("nickname-valid-flag");
const confirmPasswordValidFlag = document.getElementById("confirm-password-valid-flag");
const emailDupFlag =  document.getElementById("email-dup-flag");

const username = document.getElementById("username");
const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");
const nickname = document.getElementById("nickname");
const email = document.getElementById("email");
const provider = document.getElementById("provider");

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
        alert("Please, check the username.");

        return false;
    }

    if(confirmPasswordValidFlag.value === Check.UNCHECKED) {
        alert("Please, check the password.")

        return false;
    }

    if(nicknameValidFlag.value === Check.UNCHECKED) {
        alert("Please, check the nickname.")

        return false;
    }

    if(emailDupFlag.value === Check.UNCHECKED) {
        alert("Please, check the email.");

        return false;
    }

    return true;
}

function changeUsername() {
    usernameValidFlag.value = Check.UNCHECKED;
}

function changePassword() {
    if(password.value !== confirmPassword.value) {
        confirmPasswordValidFlag.value = Check.UNCHECKED;

        return;
    }

    confirmPasswordValidFlag.value = Check.CHECKED;
}

function changeNickname() {
    nicknameValidFlag.value = Check.UNCHECKED;
}

function changeEmail() {
    emailDupFlag.value = Check.UNCHECKED;
}

function usernameValidCheck() {
    let body = JSON.stringify({
        "username": username.value,
    });

    function success() {
        alert("사용할 수 있는 아이디입니다.")

        usernameValidFlag.value = Check.CHECKED;
    }

    function fail() {
        alert("사용할 수 없는 아이디입니다.")
    }

    let url = "/sign-up/username";
    validCheckRequest(url, body, success, fail);
}

function nicknameValidCheck() {
    let body = JSON.stringify({
        "nickname": nickname.value,
    });

    function success() {
        alert("사용할 수 있는 닉네임입니다.");

        nicknameValidFlag.value = Check.CHECKED;
    }

    function fail() {
        alert("사용할 수 없는 닉네입입니다.");
    }

    let url = "/sign-up/nickname";
    validCheckRequest(url, body, success, fail);
}

function emailDupCheck() {
    let body = JSON.stringify({
        "email": email.value,
    });

    function success() {
        alert("사용할 수 있는 이메일입니다.");

        emailDupFlag.value = Check.CHECKED;
    }

    function fail() {
        alert("이미 가입된 이메일입니다.");
    }

    let url = "/sign-up/email";
    validCheckRequest(url, body, success, fail);
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