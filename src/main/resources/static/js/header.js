const logoutBtn = document.getElementById("logout-btn");
const token = getKey("token");

if(logoutBtn) {
    logoutBtn.addEventListener("click", () => {
        localStorage.removeItem("access_token");

        let form = document.createElement("form");
        form.setAttribute("method", "POST");
        form.setAttribute("action", "/logout");

        document.body.appendChild(form);

        form.submit();
    });
}

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
