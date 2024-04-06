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

const token = getKey("token");
if (token) {
    localStorage.setItem("access_token", token);
}

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