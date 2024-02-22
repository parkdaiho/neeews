const logoutBtn = document.getElementById("logout-btn");
const token = getKey("token");

if(logoutBtn) {
    logoutBtn.addEventListener("click", () => {
        localStorage.removeItem("access_token");

        fetch("/logout", {
            method: "GET",
            headers: {
                "Content-type": "application/json",
            }
        })
            .then(response => {
                if(response.ok) {
                    let headers = response.headers;
                    location.replace(headers.get("Location"));
                }
            })
    });
}

if(token) {
    localStorage.setItem("access_token", token);
}

function getKey(key) {
    return new URLSearchParams(location.search).get(key);
}
