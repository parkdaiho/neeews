function changeSearchDomain(selectedValue) {
    let form = document.getElementById("index-form");
    let input = document.getElementById("index-form-input");

    let sort = document.getElementById("sort");

    let articleSortArr = ["sim", "MOST-SIMILAR", "date", "LATEST"];
    let postSortArr = ["title", "TITLE", "contents", "CONTENTS", "writer", "WRITER"];

    let selectedArr;
    if(selectedValue === "article") {
        selectedArr = articleSortArr;
        form.setAttribute("action", "/searched-articles");
        input.setAttribute("placeholder", "Search articles");
    } else {
        selectedArr = postSortArr;
        form.setAttribute("action", "/posts");
        input.setAttribute("placeholder", "Search post");
    }

    sort.options.length = 0;

    for(let i = 0; i < selectedArr.length; i += 2) {
        var opt = document.createElement("option");
        opt.value = selectedArr[i];
        opt.innerHTML = selectedArr[i + 1];

        sort.appendChild(opt);
    }
}

function getIndexArticles(order) {
    let articlesPart = document.getElementById("index-articles-part");

    indexRequest("article", order, articlesPart);
}

function getIndexPosts(order) {
    let postsPart = document.getElementById("index-posts-part");

    indexRequest("post", order, postsPart);
}

function indexRequest(domain, order, area) {
    let paramJson = {
        "domain": domain,
        "order": order
    };

    let param = new URLSearchParams(paramJson);
    let url = "/items?" + param;

    fetch(url)
        .then(response => {
            if(response.ok) {
                return response.text();
            } else {
                alert("load " + domain + " fail");
            }
        })
        .then(result => {
            area.innerHTML = result;
        });
}