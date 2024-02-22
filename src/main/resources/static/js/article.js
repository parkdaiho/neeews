const commentBtn = document.getElementById("comment-btn");

if(commentBtn) {
    commentBtn.addEventListener("click", () => {
        let body = JSON.stringify({
            "articleId": document.getElementById("article-id").value,
            "contents": document.getElementById("contents").value
        });

        fetch("/api/new-comment", {
            method: "POST",
            headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token"),
                "Content-type": "application/json",
            },
            body: body
        })
            .then(response => {
                if(response.ok) {
                    alert("등록완료");
                }
            })
    });
}