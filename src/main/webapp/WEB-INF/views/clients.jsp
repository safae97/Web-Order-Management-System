<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Clients</title>
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
    <script>
        // Function to show the modal alert
        function showModal(message) {
            var modal = document.getElementById("alertModal");
            var messageElement = document.getElementById("alertMessage");
            messageElement.innerText = message;
            modal.style.display = "block";
        }

        // Function to close the modal
        function closeModal() {
            var modal = document.getElementById("alertModal");
            modal.style.display = "none";
        }
    </script>
</head>
<body>

<h2>Liste des Clients</h2>
<!-- JavaScript Alert for Deletion Restriction -->
<c:if test="${not empty showAlert}">
    <script type="text/javascript">
        alert("${alertMessage}");
    </script>
</c:if>
<!-- Displaying clients in a table -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client" items="${clients}">
        <tr id="client-${client.id}">
            <td>${client.id}</td>
            <td contenteditable="true" id="nom-${client.id}">${client.nom}</td>
            <td contenteditable="true" id="prenom-${client.id}">${client.prenom}</td>
            <td contenteditable="true" id="email-${client.id}">${client.email}</td>
            <td>
                <!-- Update client -->
                <button onclick="updateClient(${client.id})">Update</button>

                <!-- Delete client -->
                <form action="clients" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${client.id}">
                    <button type="submit" style="background-color:#f44336;">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Ajouter un Client</h3>
<div class="form-container">
    <form action="clients" method="post">
        <input type="hidden" name="action" value="add">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="prenom">Prenom:</label>
        <input type="text" id="prenom" name="prenom" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <button type="submit" class="form-button">Ajouter Client</button>
    </form>
</div>

<script>
    // Function to handle inline editing and updating a client
    function updateClient(clientId) {
        var nom = document.getElementById('nom-' + clientId).innerText;
        var prenom = document.getElementById('prenom-' + clientId).innerText;
        var email = document.getElementById('email-' + clientId).innerText;

        var form = document.createElement('form');
        form.action = 'clients';
        form.method = 'POST';

        var actionInput = document.createElement('input');
        actionInput.type = 'hidden';
        actionInput.name = 'action';
        actionInput.value = 'update';
        form.appendChild(actionInput);

        var idInput = document.createElement('input');
        idInput.type = 'hidden';
        idInput.name = 'id';
        idInput.value = clientId;
        form.appendChild(idInput);

        var nomInput = document.createElement('input');
        nomInput.type = 'hidden';
        nomInput.name = 'nom';
        nomInput.value = nom;
        form.appendChild(nomInput);

        var prenomInput = document.createElement('input');
        prenomInput.type = 'hidden';
        prenomInput.name = 'prenom';
        prenomInput.value = prenom;
        form.appendChild(prenomInput);

        var emailInput = document.createElement('input');
        emailInput.type = 'hidden';
        emailInput.name = 'email';
        emailInput.value = email;
        form.appendChild(emailInput);

        document.body.appendChild(form);
        form.submit();
    }
</script>

</body>
</html>
