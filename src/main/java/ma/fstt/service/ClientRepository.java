package ma.fstt.service;

import ma.fstt.entities.Client;
import java.util.List;

public interface ClientRepository {
    Client getClientById(int id);
    void ajouterClient(Client client);
    List<Client> listerClients();
    void supprimerClient(int id);
     Client authenticate(String email, String nom);
}
