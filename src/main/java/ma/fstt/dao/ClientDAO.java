package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ClientDAO implements ClientRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ClientDAO() {
        this.emf = Persistence.createEntityManagerFactory("myentity");
        this.em = emf.createEntityManager();
    }

    @Override
    public  Client getClientById(int id) {
        return em.find(Client.class, id);
    }

    @Override
    public void ajouterClient(Client client) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(client);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    @Override
    public Client authenticate(String email, String nom) {
        try {
            System.out.println("Authenticating client with email: " + email + ", nom: " + nom);

            return em.createQuery("SELECT c FROM Client c WHERE c.email = :email AND c.nom = :nom", Client.class)
                    .setParameter("email", email)
                    .setParameter("nom", nom)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No client found with email: " + email + ", nom: " + nom);
            return null;
        } catch (Exception e) {
            System.err.println("Error during authentication: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Client> listerClients() {
        try {
            return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }

    @Override
    public void supprimerClient(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Client client = em.find(Client.class, id);
            if (client != null) {
                em.remove(client);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void mettreAJourClient(Client client) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(client);  // This will update the existing client in the database
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    // Check if the client has any orders before deleting
    public boolean clientHasOrders(int clientId) {
        try {
            long count = (long) em.createQuery("SELECT COUNT(c) FROM Commande c WHERE c.client.id = :clientId")
                    .setParameter("clientId", clientId)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
