const goUpdate = (product_number) => {
    location.href = "/restful/update/" + product_number;
};

const goSubmit = (product_number) => {
    let product_name = document.getElementById("product_name").value;
    let inventory = document.getElementById("inventory").value;
    let price = document.getElementById("price").value;

    fetch(`http://localhost:8081/restful/api/products/${product_number}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        },
        body: JSON.stringify({
            product_number,
            product_name,
            inventory,
            price
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network Error!!");
            }
            location.href = "/restful/list";
        })
        .catch(err => console.log("error ::::", err));
};