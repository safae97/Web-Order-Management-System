<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products Space</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        button {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .form-container {
            margin: 20px 0;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 60%;
            margin-left: auto;
            margin-right: auto;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-button {
            background-color: #008CBA;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
        }
        .form-button:hover {
            background-color: #006F8E;
        }
    </style>
</head>
<body>

<h2>Liste des Produits</h2>

<!-- Displaying products in a table -->
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nom</th>
        <th>Prix</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="produit" items="${produits}">
    <tr id="produit-${produit.id}">
    <td>${produit.id}</td>
    <td  id="nom-${produit.id}">${produit.nom}</td>
    <td  id="prix-${produit.id}">${produit.prix}</td>
    </tr>
</c:forEach>
    </tbody>
</table>

<h3>Creer Commande</h3>
<div class="form-container">
    <form action="${pageContext.request.contextPath}/commandes" method="post">
        <input type="hidden" name="action" value="add_com">
        <h3>Products:</h3>
        <div id="produits">
            <!-- Dynamically add input fields for product IDs and quantities -->
            <div class="product-item">
                <label for="clientId">Client ID:</label>
                <input type="number" name="clientId" id="clientId" required>
                <label for="produitId">Product ID:</label>
                <input type="number" name="produitId[]" id="produitId" required>
                <label for="quantite">Quantity:</label>
                <input type="number" name="quantities[]" id="quantite" min="1" required>
            </div>
        </div>
        <button type="button" onclick="addProduct()">Add Another Product</button>
        <button type="submit">Create Commande</button>
    </form>
</div>

<script>
    function addProduct() {
        const productSection = document.getElementById("produits");

        // Create container for the new product item
        const newProductItem = document.createElement("div");
        newProductItem.classList.add("product-item");

        // Product ID field
        const labelProdId = document.createElement("label");
        labelProdId.textContent = "Product ID:";
        const inputProdId = document.createElement("input");
        inputProdId.type = "number";
        inputProdId.name = "produitId[]";
        inputProdId.required = true;

        // Quantity field
        const labelQuantity = document.createElement("label");
        labelQuantity.textContent = "Quantity:";
        const inputQuantity = document.createElement("input");
        inputQuantity.type = "number";
        inputQuantity.name = "quantities[]";
        inputQuantity.min = 1;
        inputQuantity.required = true;

        // Append fields to the new product item
        newProductItem.appendChild(labelProdId);
        newProductItem.appendChild(inputProdId);
        newProductItem.appendChild(labelQuantity);
        newProductItem.appendChild(inputQuantity);

        // Add the new product item to the product section
        productSection.appendChild(newProductItem);
    }
</script>


</body>
</html>
