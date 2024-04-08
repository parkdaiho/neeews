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

    if(role === 'admin') {
        options[0].selected = true;
    } else if(role === 'manager') {
        options[1].selected = true;
    } else {
        options[2].selected = true;
    }
}

function getUsersBySort(sort) {
    let area = document.getElementById("my-page-area");

    let url = "/membership&sort=" + sort;
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