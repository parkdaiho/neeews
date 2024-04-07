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

function setSelected(role) {
    let options = this.childNodes;
    if (role === Role.ADMIN) {
        this.selected(options[0]);
    } else if (role === Role.MANAGER) {
        this.selected(options[1]);
    } else {
        this.selected(options[2]);
    }
}