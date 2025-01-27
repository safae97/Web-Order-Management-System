package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lignes_de_commande")
public class LignedeCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @Column(name = "quantite", nullable = false)
    private int quantite;

    public void setCommandeId(int commandeId) {
        this.commande.setId(commandeId);
    }

    public void setProduitId(int i) {
        this.produit.setId(i);
    }

    public int getProduitId() {
        return this.produit.getId();
    }
    public int getCommandeId() {
        return this.commande.getId();
    }

    public void setQuantity(int quantity) {
        this.quantite = quantity;
    }
}
