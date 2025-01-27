package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom", nullable = false, length = 100) // Specify string length
    private String nom;

    @Column(name = "prix", nullable = false, precision = 10) // Precision and scale for decimal
    private double prix;

}
