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
        input[type="text"], input[type="email"] {
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

<!-- Displaying clients in a table -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="produit" items="${produits}">
        <tr id="produit-${produit.id}">
            <td>${produit.id}</td>
            <td contenteditable="true" id="nom-${produit.id}">${produit.nom}</td>
            <td contenteditable="true" id="prix-${produit.id}">${produit.prix}</td>
            <td>
                <!-- Update PROD -->
                <button onclick="updateProduct(${produit.id})">Update Product</button>

                <!-- Delete client -->
                <form action="produits" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete_prod">
                    <input type="hidden" name="id" value="${produit.id}">
                    <button type="submit" style="background-color:#f44336;">Delete Product</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Ajouter un Produit</h3>
<div class="form-container">
    <form action="produits" method="post">
        <input type="hidden" name="action" value="add_prod">
        <label for="nom">Nom de Produit:</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="prix">Prix de Produit:</label>
        <input type="text" id="prix" name="prix" required><br>



        <button type="submit" class="form-button">Ajouter Produit</button>
    </form>
</div>

<script>
    // Function to handle inline editing and updating a client
    function updateProduct(produitId) {
        var nom = document.getElementById('nom-' + produitId).innerText.trim();
        var prix = document.getElementById('prix-' + produitId).innerText.trim();

        // Validate fields before submitting
        if (nom === "" || prix === "") {
            alert("Nom and Prix cannot be empty");
            return;
        }

        // Create and submit form dynamically
        var form = document.createElement('form');
        form.action = 'produits';
        form.method = 'POST';

        form.appendChild(createHiddenInput('action', 'update_prod'));
        form.appendChild(createHiddenInput('id', produitId));
        form.appendChild(createHiddenInput('nom', nom));
        form.appendChild(createHiddenInput('prix', prix));

        document.body.appendChild(form);
        form.submit();
    }

    function createHiddenInput(name, value) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = name;
        input.value = value;
        return input;
    }

</script>

</body>
</html>
