function logout() {
    localStorage.removeItem("access_token");

    let form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "/logout");

    document.body.appendChild(form);

    form.submit();
}