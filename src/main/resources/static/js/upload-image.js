function addFileInput() {
    const uploadArea = document.getElementById("upload-image-area");

    let newFileArea = document.createElement("div");
    newFileArea.setAttribute("class", "upload-image");

    let newFileBtnArea = document.createElement("div");
    newFileBtnArea.setAttribute("class", "upload-image-btn-area");

    let addFileInput = document.createElement("input");
    addFileInput.setAttribute("class", "files");
    addFileInput.setAttribute("type", "file");
    addFileInput.setAttribute("accept", "image/*")

    let removeNewFileAreaButton = document.createElement("button");
    removeNewFileAreaButton.innerHTML = "X";
    removeNewFileAreaButton.addEventListener("click", () => {
            newFileArea.remove();
        }
    );

    let thumbnailArea = document.createElement("div");
    thumbnailArea.setAttribute("class", "upload-image-thumbnail");

    addFileInput.addEventListener("change", () => {
        setThumbnail(event, thumbnailArea);
    });

    newFileBtnArea.append(addFileInput);
    newFileBtnArea.append(removeNewFileAreaButton);
    newFileArea.append(newFileBtnArea);
    newFileArea.append(thumbnailArea);

    uploadArea.append(newFileArea);
}

function setThumbnail(event, thumbnailArea) {
    let imageReader = new FileReader();

    imageReader.onload = function (event) {
        thumbnailArea.innerHTML = "";
        let thumbnail = document.createElement("img");
        thumbnail.setAttribute("src", event.target.result);
        thumbnail.setAttribute("style", "max-width:180px;max-height:180px;")
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
