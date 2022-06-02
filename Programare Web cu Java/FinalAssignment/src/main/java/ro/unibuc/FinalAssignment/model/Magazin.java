package ro.unibuc.FinalAssignment.model;

import javax.persistence.*;


@Entity
@Table(name = "magazine")
public final class Magazin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String magazin_denumire;

    @Column(name = "adresa_id", nullable = false)
    private long adresa;



    public Magazin() {
    }

    public Magazin(long id, String magazin_denumire, long adresa) {
        this.id = id;
        this.magazin_denumire = magazin_denumire;
        this.adresa = adresa;
    }

    public Magazin(String magazin_denumire, long adresa) {
        this.magazin_denumire = magazin_denumire;
        this.adresa = adresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMagazin_denumire() {
        return magazin_denumire;
    }

    public void setMagazin_denumire(String magazin_denumire) {
        this.magazin_denumire = magazin_denumire;
    }

    public long getAdresa() {
        return adresa;
    }

    public void setAdresa(long adresa) {
        this.adresa = adresa;
    }
}
