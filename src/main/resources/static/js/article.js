const searchButton = document.getElementById("search-btn");

if(searchButton) {
    searchButton.addEventListener("click", () => {
        let body = JSON.stringify({
            searchParam: document.getElementById("search-param").value,
            searchSort: document.getElementById("search-sort").value
        });

        fetch("/articles/search", {
            method: "POST",
            headers: {
                "Content-type": "application/json",
            },
            body: body,
        })
            .then(response => {
                if(response.ok) {

                }
            })
    })
}