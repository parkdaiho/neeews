function setSortSelected(sort) {
    let sortSelect = document.getElementById("membership-users-sort-select");
    let options = sortSelect.children;

    if (sort === Role.ADMIN) {
        options[1].selected = true;
    } else if (sort === Role.MANAGER) {
        options[2].selected = true;
    } else if(sort === Role.USER) {
        options[3].selected = true;
    } else {
        options[4].selected = true;
    }
}

function changeRole(id, role, newRole) {
    let message = "Are you sure change role " + role + " -> " + newRole + "?";
    let answer = confirm(message);

    let body = JSON. stringify({
        id: id,
        newRole: newRole,
    });

    function success() {
        alert('success');
    }

    function fail() {
        alert('fail');
    }

    if(answer) {
        apiRequest('/api/membership', Method.POST, body, getHeaders(true), success, fail);
    } else {
       setRole(id, role);
    }
}

function setRole(id, role) {
    let selectEl = document.getElementById("user-role-" + id);
    let options = selectEl.children;

    if(role === Role.ADMIN) {
        options[0].selected = true;
    } else if(role === Role.MANAGER) {
        options[1].selected = true;
    } else {
        options[2].selected = true;
    }
}

function getUsersBySort(sort) {
    let area = document.getElementById("my-page-area");

    let url = "/my-page/membership?sort=" + sort;
    fetch(url, {
        method: Method.GET,
        headers: getHeaders(false),
    })
        .then(response => {
            if(response.ok) {
                return response.text();
            } else {
                alert("Load pages fail.");
            }
        })
        .then(result => {
            area.innerHTML = result;
        });
}

function searchUser() {
    let sort = document.getElementById("membership-users-sort-select").value;
    let searchSort = document.getElementById("search-sort").value;
    let query = document.getElementById("query").value;
    let params = new URLSearchParams({
        "sort": sort,
        "searchSort": searchSort,
        "query": query
    });

    let url = "/my-page/membership?" + new URLSearchParams(params);

    fetch(url, {
        method: Method.GET,
        headers: getHeaders(false),
    })
        .then(response => {
            if(response.ok) {
                return response.text();
            }
        })
        .then(result => {
            area.innerHTML = result;
        })
}

function getPages(page, sort, searchSort, query) {

    getMembershipPage(page, sort, searchSort, query);
}

function withdraw(userId, page, sort, query) {
    let flag = confirm("Are you sure you want to withdraw this member?");
    if(!flag) return;

    let body = JSON.stringify({
        "userId": userId,
    });

    function success() {
        alert("success.");
        getPages(page, sort, query);
    }

    function fail() {
        alert("fail");
    }

    apiRequest("/api/membership", Method.DELETE, body, getHeaders(true), success, fail);
}