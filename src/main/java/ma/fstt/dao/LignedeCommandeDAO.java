package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import ma.fstt.entities.LignedeCommande;
import ma.fstt.entities.Produit;
import ma.fstt.service.LignedeCommandeRepository;

import java.util.List;

@ApplicationScoped
public class LignedeCommandeDAO implements LignedeCommandeRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public LignedeCommandeDAO() {
        this.emf = Persistence.createEntityManagerFactory("myentity");
        this.em = emf.createEntityManager();
    }

    @Override
    public LignedeCommande getLigneDeCommandeById(int id) {
        return em.find(LignedeCommande.class, id);
    }

    @Override
    public void ajouterLigneDeCommande(LignedeCommande ligneDeCommande) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(ligneDeCommande);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void supprimerLigneDeCommande(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            LignedeCommande ligne = em.find(LignedeCommande.class, id);
            if (ligne != null) {
                em.remove(ligne);
            } else {
                System.out.println("LignedeCommande not found with id: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void mettreAJourLigneDeCommande(LignedeCommande ligneDeCommande) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(ligneDeCommande);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<LignedeCommande> listerLignesDeCommande() {
        return em.createQuery("SELECT l FROM LignedeCommande l", LignedeCommande.class).getResultList();
    }
}
