function withdrawal(userId) {
    let password = document.getElementById("password").value;
    if(password === "") {
        alert("Please, input your password");
    }

    let body = JSON.stringify({
        "userId": userId,
        "password": password,
    });

    let flag = confirm(
        "If you leave, all information related to the member will be deleted. Do you want to proceed?"
    );
    if(!flag) return;

    function success() {
        alert("Thanks for using our site so far.");
        localStorage.removeItem("access_token");
        location.replace("/");
    }

    function fail() {
        alert("비밀번호를 확인해주세요.");
    }

    apiRequest("/api/withdrawal", Method.DELETE, body, getHeaders(true), success, fail);
}