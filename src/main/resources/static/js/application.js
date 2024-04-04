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
if(token) {
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
        if(dic[0] === key) {
            result = dic[1];
            return true
        }
    });

    return result;
}
