console.log("Hello from main.js");
function sendData() {
    const fileInput = document.getElementById("csvFileInput");

    if (fileInput) {
        const formData = new FormData();
        console.log(formData);
        formData.append("file", fileInput.files[0]);

        fetch("/data", {
            method: "POST",
            body: formData,
        })
            .then((response) => response.text())
            .then((data) => {
                const inputEl = document.getElementById("table_response");
                inputEl.value = data;
            })
            .catch((error) => {
                console.error("Error uploading file:", error);
                // Handle errors
            });
    } else {
        console.error("No file selected");
        // Handle the case where no file is selected
    }
}
