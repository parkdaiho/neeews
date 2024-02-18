const signUpBtn = document.getElementById("sign_up_btn");

if(signUpBtn) {
    signUpBtn.addEventListener("click", () => {
        let body = JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value,
            nickname: document.getElementById("nickname").value,
            email: document.getElementById("email").value,
        })
        fetch("/sign-up", {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: body
        })
            .then(response => {
                if(response.ok) {
                    let headers = response.headers;
                    location.replace(headers.get("Location"));
                }
            });
    });
}