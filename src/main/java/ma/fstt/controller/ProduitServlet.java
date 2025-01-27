// src/main/java/ma/fstt/controller/ProduitServlet.java
package ma.fstt.controller;

import jakarta.enterprise.inject.spi.CDI;
import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Client;
import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitRepository;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitRepository produitRepository;
    @Inject
    private ProduitDAO ProduitDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ProduitDAO = CDI.current().select(ProduitDAO.class).get();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> produits = ProduitDAO.listerProduits();
        request.setAttribute("produits", produits);
        request.getRequestDispatcher("/WEB-INF/views/produits.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete_prod".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                int produitId = Integer.parseInt(idStr.trim());
                ProduitDAO.supprimerProduit(produitId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID for deletion.");
                return;
            }

        } else if ("update_prod".equals(action)) {
            String idStr = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prixStr = request.getParameter("prix");

            if (idStr != null && !idStr.trim().isEmpty() && prixStr != null && !prixStr.trim().isEmpty() && nom != null && !nom.trim().isEmpty()) {
                int id = Integer.parseInt(idStr.trim());
                double prix = Double.parseDouble(prixStr.trim());

                Produit produit = new Produit();
                produit.setId(id);
                produit.setNom(nom);
                produit.setPrix(prix);

                ProduitDAO.mettreAJourProduit(produit);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid input for update.");
                return;
            }

        } else if ("add_prod".equals(action)) {
            String nom = request.getParameter("nom");
            String prixStr = request.getParameter("prix");

            if (nom != null && !nom.trim().isEmpty() && prixStr != null && !prixStr.trim().isEmpty()) {
                double prix = Double.parseDouble(prixStr.trim());

                Produit prod = new Produit();
                prod.setNom(nom);
                prod.setPrix(prix);

                ProduitDAO.ajouterProduit(prod);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product data.");
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/produits");
    }
}
