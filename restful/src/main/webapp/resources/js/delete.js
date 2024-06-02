const goDel = (product_number) => {
    let deleteUrl = "http://localhost:8081/restful/api/products/" + product_number
    fetch(deleteUrl, {
        method: "DELETE"
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            location.replace("/restful/list");
        })
        .catch(err => console.log("error ::::", err));
};