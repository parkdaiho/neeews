const clippings = document.getElementsByClassName("clippings-checkbox");

function cancelClipping() {
    let clippingIdArr = [];

    for(let i = 0; i < clippings.length; i++) {
        let clipping = clippings[i];

        if(clipping.checked) {
            let clippingId = clipping.id;

            clippingIdArr.push(clippingId.split("-")[1]);
        }
    }

    let url = "/api/clipping";
    let body = JSON.stringify({
        "articleIdArr": clippingIdArr,
    });

    function success() {
        alert("success");
        location.reload();
    }

    function fail() {
        alert("fail");
    }

    apiRequest(url, Method.DELETE, body, getHeaders(true), success, fail);
}

function showClipping(articleId) {
    let url = "/articles/" + articleId;

    location.replace(url);
}