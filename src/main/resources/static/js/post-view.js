function goToModifyPage() {
    location.href = "/post?id=" + id;
}

function deletePost() {
    let url = "/api/posts/" + id;

    fetch(url, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("access_token"),
        },
    })
        .then(response => {
            if(response.ok) {
                let headers = response.headers;

                location.href = headers.get("Location");
            }
        });
}