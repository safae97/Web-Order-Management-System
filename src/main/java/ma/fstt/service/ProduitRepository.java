package ma.fstt.service;

import ma.fstt.entities.Produit;
import java.util.List;

public interface ProduitRepository {
    Produit trouverById(int id);
    void ajouterProduit(Produit produit);
    List<Produit> listerProduits();
    void supprimerProduit(int id);

    Produit getProduitById(int produitId);
}
