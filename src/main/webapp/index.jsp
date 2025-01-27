<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD Navigation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 30px;
        }
        p {
            margin-bottom: 20px;
            font-size: 18px;
        }
        .nav-button {
            width: 80%;
            max-width: 400px;
            padding: 15px;
            margin: 10px auto;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            display: block;
            text-transform: uppercase;
            transition: background-color 0.3s;
        }
        .nav-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1>Welcome to the CRUD Application</h1>
<p>Select an option to manage:</p>

<button class="nav-button" onclick="location.href='commandes/list'">Manage Commandes</button>
<button class="nav-button" onclick="location.href='clients'">Manage Clients</button>
<button class="nav-button" onclick="location.href='produits'">Manage Produits</button>
<button class="nav-button" onclick="location.href='commandes/create'">Create Commande</button>

</body>
</html>
