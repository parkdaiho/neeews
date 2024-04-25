const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirm-password");
const nickname = document.getElementById("nickname");

const confirmPasswordValidFlag = document.getElementById("confirm-password-valid-flag");
const nicknameValidFlag= document.getElementById("nickname-valid-flag");


function changeNickname(originalNickname) {
    if(nickname.value !== originalNickname) {
        nicknameValidFlag.value = Check.UNCHECKED;
    }
}

function nicknameDupCheck() {
    let body = JSON.stringify({
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
                if(result.identification === true) {
                    nicknameValidFlag.value = Check.CHECKED;
                    alert("닉네임을 변경해주세요.");

                    return;
                }

                if (result.flag === true) {
                    nicknameValidFlag.value = Check.CHECKED;
                    alert("사용 가능한 닉네임입니다.");
                } else {
                    nicknameValidFlag.value = Check.UNCHECKED;
                    alert("이미 존재하는 닉네임입니다.");
                }
            }
        )
}

function changePassword() {
    if(password.value !== confirmPassword.value) {
        confirmPasswordValidFlag.value = Check.UNCHECKED;

        return;
    }

    confirmPasswordValidFlag.value = Check.CHECKED;
}

function checkFlag() {
    if (confirmPasswordValidFlag.value === Check.UNCHECKED) {
        alert("Please, check the password.");

        return false;
    }

    if (nicknameValidFlag.value === Check.UNCHECKED) {
        alert("Please, check the nickname");

        return false;
    }

    return true;
}

function modifyUser(userId) {
    if(!checkFlag()) return;

    let body = JSON.stringify({
        "userId": userId,
        "password": password.value,
        "nickname": nickname.value,
    });

    function success() {
        alert("정보를 수정했습니다.");
        location.reload();
    }

    function fail() {
        alert("정보 수정에 실패했습니다.");
    }

    apiRequest("/api/information", Method.PUT, body, getHeaders(true), success, fail);
}