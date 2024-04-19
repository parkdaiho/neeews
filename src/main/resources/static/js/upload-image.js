function addFileInput() {
    const uploadArea = document.getElementById("upload-image-area");

    let newFileArea = document.createElement("div");
    newFileArea.setAttribute("class", "upload-image");

    let addFileInput = document.createElement("input");
    addFileInput.setAttribute("class", "files");
    addFileInput.setAttribute("type", "file");
    addFileInput.setAttribute("accept", "image/*")

    let thumbnailArea = document.createElement("div");
    thumbnailArea.setAttribute("class", "upload-image-thumbnail");

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

function deleteEmptyFileInput() {
    let files = document.getElementsByClassName("files");
    let filesArr = Array.from(files);

    for (let i = 0; i < filesArr.length; i++) {
        let file = filesArr[i];
        if (file.files[0] == null) {
            file.parentElement.remove();
        }
    }
}
