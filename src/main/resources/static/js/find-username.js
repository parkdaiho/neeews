function sendCodeForUsername() {
    loading();

    let codeArea = document.getElementById("find-user-info-code");
    let email = document.getElementById("email");

    let body = JSON.stringify({
        "email": email.value,
    });
    let url = "/api/username";
    fetch(url, {
        headers: getHeaders(true),
        method: Method.POST,
        body: body,
    })
        .then(response => {
            if(response.ok) {
                loading();

                email.setAttribute("readonly", "readonly");
                codeArea.style.display = "";

                let sendBtn = document.getElementById("find-username-send-btn");
                sendBtn.style.display = "none";

                let authBtn = document.getElementById("find-username-auth-btn");
                authBtn.style.display = "";
            } else {
                loading();
                alert("Check email");
            }
        });
}

function authenticateCodeForUsername() {
    let email = document.getElementById("email");
    let code = document.getElementById("code");

    let body = JSON.stringify({
        "email": email.value,
        "code": code.value,
    });
    let url = "/api/username/authentication";
    fetch(url, {
        method: Method.POST,
        headers: getHeaders(true),
        body: body,
    })
        .then(response => {
            if(response.ok) {
                getFindUsernameResultPage();
            } else {
                alert("Fail authentication.");
            }
        });
}