const Method = {
    GET: "GET",
    POST: "POST",
    PUT: "PUT",
    DELETE: "DELETE"
}

const Role = {
    ADMIN: "ADMIN",
    MANAGER: "manager",
    USER: "USER"
}

const Check = {
    CHECKED: "checked",
    UNCHECKED: "unchecked"
}

const token = getKey("token");
if (token) {
    localStorage.setItem("access_token", token);
}

// sign-up, member-info
const usernameRegex = /^[a-z0-9]{5,20}$/;
const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!&*_+=-])[a-zA-Z0-9@#$%!&*_+=-]{8,20}$/;
const nicknameRegex = /^.{2,16}$/;

const validPasswordMessage = "사용가능한 비밀번호입니다.";
const validNicknameMessage = "사용가능한 닉네임입니다.";

const nicknameDupFailMessage = "이미 사용중인 닉네임입니다.";

const usernameRegexFailMessage = "아이디는 5~20자의 영문 소문자, 숫자만 사용가능합니다.";
const passwordRegexFailMessage = "비밀번호는 영어 소문자, 대문자, 숫자, 특수기호를 모두 포함하는 최소 8자 이상이어야 합니다.";
const nicknameRegexFailMessage = "닉네임의 길이는 2~16자입니다.";

const passwordConfirmFailMessage = "비밀번호를 확인해주세요.";

const uncheckedUsernameMessage = "Please, check the username.";
const uncheckedPasswordMessage = "Please, check the password.";
const uncheckedNicknameMessage = "Please, check the nickname.";
const uncheckedEmailMessage = "Please, check the email";

// new-post, new-notice, comments-area
const blankTitleMessage = "제목을 입력해주세요.";
const blankTextMessage = "내용을 입력해주세요.";

function getKey(key) {
    return new URLSearchParams(location.search).get(key);
}

function getCookie(key) {
    let result = null;

    let cookies = document.cookie.split(";");
    cookies.some((cookie) => {
        cookie = cookie.replace(" ", "");

        let dic = cookie.split("=");
        if (dic[0] === key) {
            result = dic[1];
            return true
        }
    });

    return result;
}

function getHeaders(jsonFlag) {
    let headers = {};
    if (jsonFlag) headers["Content-type"] = "application/json";

    let accessToken = localStorage.getItem("access_token");
    if (accessToken) headers["Authorization"] = "Bearer " + accessToken;

    return headers;
}

function apiRequest(url, method, body, headers, success, fail) {
    fetch(url, {
        method: method,
        headers: headers,
        body: body,
    })
        .then(response => {
            if (response.ok) {
                return success();
            } else {
                return fail();
            }
        });
}
