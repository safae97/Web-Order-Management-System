package ma.fstt.controller;

import jakarta.enterprise.inject.spi.CDI;
import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LignedeCommandeDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.LignedeCommande;
import ma.fstt.entities.Produit;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "commandes", value = "/commandes/*")
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeDAO commandeDAO;
    @Inject
    private ProduitDAO produitDAO;
    @Inject
    private ClientDAO clientDAO;
    @Inject
    private LignedeCommandeDAO lignedecommandeDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.commandeDAO = CDI.current().select(CommandeDAO.class).get();
        this.produitDAO = CDI.current().select(ProduitDAO.class).get();
        this.clientDAO = CDI.current().select(ClientDAO.class).get();
        this.lignedecommandeDAO = CDI.current().select(LignedeCommandeDAO.class).get();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo(); // Get the additional path after "/commandes"

        if (action == null || "/list".equals(action)) {
            List<LignedeCommande> lignesDeCommande = lignedecommandeDAO.listerLignesDeCommande();
            request.setAttribute("lignesDeCommande", lignesDeCommande);
            request.getRequestDispatcher("/WEB-INF/views/commandes.jsp").forward(request, response);
        } else if ("/create".equals(action)) {
            List<Produit> produits = produitDAO.listerProduits();
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("/WEB-INF/views/createcommande.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update_ligne".equals(action)) {
            String ligneIdStr = request.getParameter("ligneId");
            String quantiteStr = request.getParameter("quantite");

            // Validate that parameters are not null or empty
            if (ligneIdStr != null && quantiteStr != null) {
                try {
                    int ligneId = Integer.parseInt(ligneIdStr);
                    int quantite = Integer.parseInt(quantiteStr);

                    LignedeCommande ligne = lignedecommandeDAO.getLigneDeCommandeById(ligneId);
                    if (ligne != null) {
                        ligne.setQuantite(quantite);
                        lignedecommandeDAO.mettreAJourLigneDeCommande(ligne);
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters for update.");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/commandes/list");

        } else if ("delete_ligne".equals(action)) {
            String ligneIdStr = request.getParameter("ligneId");

            if (ligneIdStr != null) {
                try {
                    int ligneId = Integer.parseInt(ligneIdStr);
                    lignedecommandeDAO.supprimerLigneDeCommande(ligneId);
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ligneId: " + e.getMessage());
                    return;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ligneId parameter.");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/commandes/list");

        } else if ("add_com".equals(action)) {
            // Ensure clientId and product data are present
            String clientIdStr = request.getParameter("clientId");
            String[] produitIds = request.getParameterValues("produitId[]");
            String[] quantities = request.getParameterValues("quantities[]");

            if (clientIdStr != null && produitIds != null && quantities != null) {
                try {
                    int clientId = Integer.parseInt(clientIdStr);
                    Client client = clientDAO.getClientById(clientId);

                    Commande newCommande = new Commande();
                    newCommande.setClient(client);
                    newCommande.setDate(new Date());

                    commandeDAO.ajouterCommande(newCommande);

                    for (int i = 0; i < produitIds.length; i++) {
                        int produitId = Integer.parseInt(produitIds[i]);
                        int quantity = Integer.parseInt(quantities[i]);

                        Produit produit = produitDAO.getProduitById(produitId);

                        LignedeCommande ligneDeCommande = new LignedeCommande();
                        ligneDeCommande.setCommande(newCommande);
                        ligneDeCommande.setProduit(produit);
                        ligneDeCommande.setQuantite(quantity);

                        lignedecommandeDAO.ajouterLigneDeCommande(ligneDeCommande);
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
                    return;
                }
                response.sendRedirect(request.getContextPath() + "/commandes/list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing order data.");
            }
        }
    }
}
