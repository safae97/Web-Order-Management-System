package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import ma.fstt.entities.Commande;
import ma.fstt.service.CommandeRepository;

import java.util.List;

@ApplicationScoped
public class CommandeDAO implements CommandeRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CommandeDAO() {
        this.emf = Persistence.createEntityManagerFactory("myentity");
        this.em = emf.createEntityManager();
    }

    @Override
    public Commande getCommandeById(int id) {
        return em.find(Commande.class, id);
    }

    @Override
    public void ajouterCommande(Commande commande) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(commande);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void supprimerCommande(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Commande commande = em.find(Commande.class, id);
            if (commande != null) em.remove(commande);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void mettreAJourCommande(Commande commande) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(commande);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    @Override
    public List<Commande> listerCommandes() {
        return em.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
    }
}
