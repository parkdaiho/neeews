function writePost() {
    deleteEmptyFileInput();

    let url = "/api/post";

    requestPost(url, Method.POST);
}

function modifyPost(postId) {
    deleteEmptyFileInput();

    let files = document.getElementsByClassName("files");
    if (files.length !== 0) {
        if (!confirm("파일을 추가하면 기존의 파일이 제거됩니다. 수정하시겠습니까?")) return;
    }

    let url = "/api/posts/" + postId;

    requestPost(url, Method.PUT);
}

function requestPost(url, method) {
    let title = document.getElementById("new-post-title");
    let text = document.getElementById("new-post-text");
    let files = document.getElementsByClassName("files");

    if(title.value === "") {
        alert("")
    }

    let formData = new FormData();

    formData.append("title", title.value);
    formData.append("text", text.innerHTML
        .replaceAll("<div>", "<br>")
        .replaceAll("</div>", ""));

    for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i].files[0]);
    }

    fetch(url, {
        method: method,
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("access_token"),
        },
        body: formData,
    })
        .then(response => {
            if (response.ok) {
                let headers = response.headers;

                location.href = headers.get("Location");
            } else {
                alert("fail");
                location.replace("/posts");
            }
        });
}