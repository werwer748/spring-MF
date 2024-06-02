const goRegister = () => {
    let product_name = document.getElementById('product_name').value;
    let inventory = document.getElementById('inventory').value;
    let price = document.getElementById('price').value;
    let manufacturer = document.getElementById('manufacturer').value;

    let formData = {
        product_name,
        inventory,
        price,
        manufacturer
    };
    fetch('http://localhost:8081/restful/api/products', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            location.href = "list";
        })
        .catch(err => console.log("error ::::", err));
};