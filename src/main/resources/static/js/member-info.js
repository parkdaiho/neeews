function changeNickname(originalNickname) {
    let flag = document.getElementById("nickname-dup-flag");
    let nickname = document.getElementById("nickname");

    flag.value = nickname.value === originalNickname;
}

function nicknameDupCheck() {
    let nickname = document.getElementById("nickname").value;
    let body = JSON.stringify({
        "nickname": nickname,
    });

    fetch("/api/nickname", {
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
                let flag = document.getElementById("nickname-dup-flag");

                if(result.identification === true) {
                    alert("닉네임을 변경해주세요.");
                    flag.value = true;
                    return;
                }

                if (result.flag === true) {
                    flag.value = true;
                    alert("사용 가능한 닉네임입니다.");
                } else {
                    alert("이미 존재하는 닉네임입니다.");
                    flag.value = false;
                }
            }
        )
}

function confirmPassword() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirm-password").value;
    let flag = document.getElementById("confirm-password-flag");

    flag.value = password === confirmPassword;
}

function checkFlag() {
    let passwordFlag = document.getElementById("confirm-password-flag").value;
    let nicknameFlag = document.getElementById("nickname-dup-flag").value;

    if (passwordFlag === 'false') {
        alert("Please, confirm password.");
        return false;
    }

    if (nicknameFlag === 'false') {
        alert("Please, check nickname duplication");
        return false;
    }

    return true;
}

function modifyUser(userId) {
    if(!checkFlag()) return;

    let password = document.getElementById("password").value;
    let nickname = document.getElementById("nickname").value;
    let body = JSON.stringify({
        "userId": userId,
        "password": password,
        "nickname": nickname,
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