package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import ma.fstt.entities.Client;
import ma.fstt.entities.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ma.fstt.service.ProduitRepository;

import java.util.List;
@ApplicationScoped
public class ProduitDAO implements ProduitRepository {

    private EntityManagerFactory emf;

    private EntityManager em;

    public ProduitDAO() {
        this.emf = Persistence.createEntityManagerFactory("myentity");

        this.em = emf.createEntityManager();
    }


    @Override
    public Produit trouverById(int id) {
        return em.find(Produit.class, id);
    }

    @Override
    public void ajouterProduit(Produit produit) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(produit);
        transaction.commit();
    }

    @Override
    public List<Produit> listerProduits() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }

    @Override
    public void supprimerProduit(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Produit produit = em.find(Produit.class, id);
        if (produit != null) {
            em.remove(produit);
        }
        transaction.commit();
    }

    @Override
    public Produit getProduitById(int produitId) {
        return em.find(Produit.class, produitId);
    }

    public void mettreAJourProduit(Produit prod) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(prod);  // This will update the existing client in the database
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
