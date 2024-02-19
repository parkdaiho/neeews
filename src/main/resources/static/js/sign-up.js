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
                    return response.json();
                }
            })
            .then(result => {
                let params = new URLSearchParams(result);
                let url = "/login?" + params.toString();
                fetch(url, {
                    method: "POST",
                })
                    .then(res => {
                        if(res.ok) {
                            location.replace("/");
                        }
                    })
            });
    });
}