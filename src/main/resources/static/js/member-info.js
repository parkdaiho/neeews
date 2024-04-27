const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");
const nickname = document.getElementById("nickname");

const confirmPasswordValidFlag = document.getElementById("confirm-password-valid-flag");
const nicknameValidFlag = document.getElementById("nickname-valid-flag");

const passwordValidCheckMessage = document.getElementById("password-valid-check-message");
const nicknameValidCheckMessage = document.getElementById("nickname-valid-check-message");

const nicknameSameAsOriginalNicknameMessage = "기존에 사용하던 닉네임입니다.";

const modifySuccessMessage = "정보 수정에 성공했습니다.";
const modifyFailMessage = "정보 수정에 실패했습니다.";

function changeNickname(originalNickname) {
    if (nickname.value !== originalNickname) {
        nicknameValidFlag.value = Check.UNCHECKED;
        nicknameValidCheckMessage.innerHTML = "";
    }
}

function nicknameDupCheck(originalNickname) {
    if (nickname.value === originalNickname) {
        alert(nicknameSameAsOriginalNicknameMessage);

        return;
    }

    if (!nicknameRegex.test(nickname.value)) {
        alert(nicknameRegexFailMessage);
        nicknameValidCheckMessage.innerHTML = nicknameRegexFailMessage;

        return;
    }

    let body = JSON.stringify({
        "originalNickname": originalNickname,
        "nickname": nickname.value,
    });

    fetch("/api/information/nickname", {
        method: Method.POST,
        headers: getHeaders(true),
        body: body,
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
        })
        .then(result => {
                if (result) {
                    alert(validNicknameMessage);

                    nicknameValidFlag.value = Check.CHECKED;
                    nicknameValidCheckMessage.innerHTML = validNicknameMessage;
                } else {
                    alert(nicknameDupFailMessage);
                }
            }
        )
}

function changePassword() {
    if (!passwordRegex.test(password.value)) {
        passwordValidCheckMessage.innerHTML = passwordRegexFailMessage;
    } else {
        passwordValidCheckMessage.innerHTML = validPasswordMessage;
    }

    if (password.value !== confirmPassword.value) {
        confirmPasswordValidFlag.value = Check.UNCHECKED;
    } else {
        confirmPasswordValidFlag.value = Check.CHECKED;
    }
}

function checkFlag() {
    if (confirmPasswordValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedPasswordMessage);

        return false;
    }

    if (nicknameValidFlag.value === Check.UNCHECKED) {
        alert(uncheckedNicknameMessage);

        return false;
    }

    return true;
}

function modifyUser(userId) {
    if (!checkFlag()) return;

    let body = JSON.stringify({
        "userId": userId,
        "password": password.value,
        "nickname": nickname.value,
    });

    function success() {
        alert(modifySuccessMessage);
        location.reload();
    }

    function fail() {
        alert(modifyFailMessage);
    }

    apiRequest("/api/information", Method.PUT, body, getHeaders(true), success, fail);
}