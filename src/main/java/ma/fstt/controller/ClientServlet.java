package ma.fstt.controller;

import jakarta.enterprise.inject.spi.CDI;
import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "clients", value = "/clients/*")
public class ClientServlet extends HttpServlet {

    @Inject
    private ClientDAO ClientDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ClientDAO = CDI.current().select(ClientDAO.class).get();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Client> clients = ClientDAO.listerClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/WEB-INF/views/clients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String clientIdStr = request.getParameter("id");

            if (clientIdStr != null && !clientIdStr.trim().isEmpty()) {
                int clientId = Integer.parseInt(clientIdStr.trim());

                // Check if the client has orders
                if (ClientDAO.clientHasOrders(clientId)) {
                    // Set flag and message for JSP to trigger alert
                    request.setAttribute("showAlert", true);
                    request.setAttribute("alertMessage", "This client has orders and cannot be deleted.");
                    // Re-fetch the client list to refresh the table
                    List<Client> clients = ClientDAO.listerClients();
                    request.setAttribute("clients", clients);
                    request.getRequestDispatcher("/WEB-INF/views/clients.jsp").forward(request, response);
                } else {
                    // Proceed with deletion if no orders are associated
                    ClientDAO.supprimerClient(clientId);
                    response.sendRedirect("clients");  // Redirect back to the clients page
                }
            }
        } else if ("update".equals(action)) {
            // Handle update logic (unchanged)
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");

            Client client = new Client();
            client.setId(id);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);

            ClientDAO.mettreAJourClient(client);
            response.sendRedirect("clients");
        } else if ("add".equals(action)) {
            // Handle adding a new client (unchanged)
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");

            if (nom != null && prenom != null && email != null) {
                Client client = new Client();
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setEmail(email);

                ClientDAO.ajouterClient(client);
                response.sendRedirect("clients");
            }
        }
    }


}
