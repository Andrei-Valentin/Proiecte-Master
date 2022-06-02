package ro.unibuc.FinalAssignment.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "produse")
public final class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String produs_categorie;

    private String produs_denumire;

    private double produs_pret;

    public Produs() {
    }

    public Produs(long id, String produs_categorie, String produs_denumire, double produs_pret) {
        this.id = id;
        this.produs_categorie = produs_categorie;
        this.produs_denumire = produs_denumire;
        this.produs_pret = produs_pret;
    }

    public Produs(String produs_categorie, String produs_denumire, double produs_pret) {
        this.produs_categorie = produs_categorie;
        this.produs_denumire = produs_denumire;
        this.produs_pret = produs_pret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProdus_categorie() {
        return produs_categorie;
    }

    public void setProdus_categorie(String produs_categorie) {
        this.produs_categorie = produs_categorie;
    }

    public String getProdus_denumire() {
        return produs_denumire;
    }

    public void setProdus_denumire(String produs_denumire) {
        this.produs_denumire = produs_denumire;
    }

    public double getProdus_pret() {
        return produs_pret;
    }

    public void setProdus_pret(double produs_pret) {
        this.produs_pret = produs_pret;
    }
}
