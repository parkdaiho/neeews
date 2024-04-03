function changeSearchDomain(selectedValue) {
    let sort = document.getElementById("sort");

    let articleSortArr = ["sim", "MOST-SIMILAR", "date", "LATEST"];
    let postSortArr = ["title", "TITLE", "contents", "CONTENTS", "writer", "WRITER"];

    let selectedArr;
    if(selectedValue === "article") selectedArr = articleSortArr;
    else selectedArr = postSortArr;

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
                area.innerHTML = response.text();
            } else {
                alert("load " + domain + " fail");
            }
        });
}