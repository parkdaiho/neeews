function getPages(page, sort, query) {

}

function showArticle(index) {
    let body = JSON.stringify({
        "title": document.getElementById("item_title_" + index).innerHTML,
        "description": document.getElementById("item_description_" + index).innerHTML,
        "originalLink": document.getElementById("item_originalLink_" + index).value,
        "link": document.getElementById("item_link_" + index).value,
        "pubDate": document.getElementById("item_pubDate_" + index).innerHTML,
    });

    fetch("/api/article", {
        method: "POST",
        headers: {
            "Content-type": "application/json",
        },
        body: body,
    })
        .then(response => {
            if(response.ok) {
                let headers = response.headers;
                location.href = headers.get("Location");
            }
        });
}