package ma.fstt.service;

import ma.fstt.entities.Commande;

import java.util.List;

public interface CommandeRepository {
    Commande getCommandeById(int id);
    void ajouterCommande(Commande commande);
    void supprimerCommande(int id);
    void mettreAJourCommande(Commande commande);
    List<Commande> listerCommandes();
}
