const commentCreateButton = document.getElementById("comment-create-button");

if(commentCreateButton) {
    commentCreateButton.addEventListener("click", () => {
        let body = JSON.stringify({
            "articleId" : document.getElementById("article-id").value,
            "contents" : document.getElementById("comment-create-contents").value,
        });

        fetch("/api/article-comment", {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("access_token"),
                "Content-type": "application/json",
            },
            body: body,
        })
            .then(response => {
                if(response.ok) {
                    alert("댓글 작성을 완료했습니다.");
                    location.reload();
                }
            })
    });
}