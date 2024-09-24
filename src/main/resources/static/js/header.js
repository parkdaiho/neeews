// const body = document.querySelector('body');
// const changeThemeBtn = document.querySelector('#change-theme-btn');
//
// changeThemeBtn.onclick = () => body.classList.toggle('dark');

function storeAccessToken(token) {
    if(token) {
        localStorage.setItem("access_token", token);
    }
}

function logout() {
    localStorage.removeItem("access_token");

    let form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "/logout");

    document.body.appendChild(form);

    form.submit();
}

function clippingsPage(login) {
    location.replace("/clippings");
}
