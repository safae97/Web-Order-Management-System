package ma.fstt.service;

import ma.fstt.entities.LignedeCommande;

import java.util.List;

public interface LignedeCommandeRepository {
    LignedeCommande getLigneDeCommandeById(int id);
    public void ajouterLigneDeCommande(LignedeCommande ligne);
    void supprimerLigneDeCommande(int id);
    void mettreAJourLigneDeCommande(LignedeCommande ligneDeCommande);
    List<LignedeCommande> listerLignesDeCommande();
}
