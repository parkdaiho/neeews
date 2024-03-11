let cnt = 1;

function addFileInput() {
    const uploadArea = document.getElementById("post-upload-area");

    let newFileArea = document.createElement("div");
    newFileArea.setAttribute("class", "newFileArea");

    let addFileInput = document.createElement("input");
    addFileInput.setAttribute("id", "file" + cnt++);
    addFileInput.setAttribute("type", "file");

    let thumbnailArea = document.createElement("div");
    thumbnailArea.setAttribute("class", "thumbnailArea");
    addFileInput.addEventListener("change", () => {
        setThumbnail(event, thumbnailArea);
    });

    let removeNewFileAreaButton = document.createElement("button");
    removeNewFileAreaButton.innerHTML = "REMOVE";
    removeNewFileAreaButton.addEventListener("click", () => {
            newFileArea.remove();
        }
    );

    newFileArea.append(addFileInput);
    newFileArea.append(thumbnailArea);
    newFileArea.append(removeNewFileAreaButton);

    uploadArea.append(newFileArea);
}

function setThumbnail(event, thumbnailArea) {
    let imageReader = new FileReader();

    imageReader.onload = function (event) {
        thumbnailArea.innerHTML = "";
        let thumbnail = document.createElement("img");
        thumbnail.setAttribute("src", event.target.result);
        thumbnail.setAttribute("style", "width:50px;height:auto;")
        thumbnailArea.append(thumbnail);
    }

    imageReader.readAsDataURL(event.target.files[0]);
}
