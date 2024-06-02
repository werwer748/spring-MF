function restProductList() {
    fetch("http://localhost:8081/restful/api/products")
        .then(response => {
            console.log('response::::', response);
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(products => {
            console.log('products::::', products);
            let productListTable = document.getElementById("productList");
            let tbody = productListTable.querySelector("tbody");
            tbody.innerHTML = "";

            products.forEach((product) => {;
                let tr = document.createElement("tr");
                const pnum = product.product_number;
                tr.innerHTML =
                    '<td>'+ pnum +'</td>' +
                    '<td><a href="detail/'+ pnum + '">'+ product.product_name +'</a></td>' +
                    '<td>'+ product.inventory +'</td>' +
                    '<td>'+ product.price +'</td>' +
                    '<td>'+ product.manufacturer +'</td>';

                tbody.appendChild(tr);
            })

        })
        .catch(error => {
            console.log('error::::', error);
        })
}