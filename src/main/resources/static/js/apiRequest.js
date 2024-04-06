const Method = {
    GET: "GET",
    POST: "POST",
    PUT: "PUT",
    DELETE: "DELETE"
};

function apiRequest(url, method, headers, body, success, fail) {
    fetch(url, {
        method: method,
        headers: headers,
        body: body,
    })
        .then(response => {
            if (response.ok) {
                success();
            } else {
                const refreshToken = getCookie("refresh_token");
                if (response.status === 401 && refreshToken) {
                    fetch("/api/token", {
                        method: Method.POST,
                        headers: {
                            "Authorization": "Bearer " + localStorage.getItem("access_token"),
                            "Content-type": "application/json",
                        },
                        body: JSON.stringify({
                            "refreshToken": refreshToken,
                        }),
                    })
                        .then(response => {
                            if (response.ok) {
                                return response.json();
                            }
                        })
                        .then(result => {
                            localStorage.setItem("access_token", result.accessToken);
                            apiRequest(url, method, headers, body, success, fail);
                        })
                } else {
                    return fail();
                }
            }
        });
}