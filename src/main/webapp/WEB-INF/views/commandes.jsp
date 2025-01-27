<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ligne de Commandes List</title>
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
        input[type="text"], select {
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

<h2>Liste des Lignes de Commandes</h2>

<!-- Displaying lignes de commandes in a table -->
<table>
    <thead>
    <tr>
        <th>Commande ID</th>
        <th>Produit Nom</th>
        <th>Produit Prix</th>
        <th>Quantité</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lignedeCommande" items="${lignesDeCommande}">
        <tr>
            <td>${lignedeCommande.commande.id}</td>
            <td>${lignedeCommande.produit.nom}</td>
            <td>${lignedeCommande.produit.prix}</td>
            <td>
                <form action="commandes" method="post" style="display:inline;">
                    <input type="number" name="quantite" value="${lignedeCommande.quantite}" min="1" required>
                    <input type="hidden" name="action" value="update_ligne">
                    <input type="hidden" name="ligneId" value="${lignedeCommande.id}">
                    <button type="submit" class="form-button">Update</button>
                </form>
            </td>
            <td>
                <!-- Delete Form -->
                <form action="commandes" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete_ligne">
                    <input type="hidden" name="ligneId" value="${lignedeCommande.id}">
                    <button type="submit" style="background-color:#f44336;">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>




</body>
</html>
