package ro.unibuc.FinalAssignment.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "adresa")
public final class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String adresa_tara;

    private String adresa_oras;

    private String adresa_strada;

    private int adresa_numarStrada;

    //@OneToOne(mappedBy = "adresa")
    //private Magazin magazin;


    public Adresa() {
    }

    public Adresa(long id, String adresa_tara, String adresa_oras, String adresa_strada, int adresa_numarStrada) {
        this.id = id;
        this.adresa_tara = adresa_tara;
        this.adresa_oras = adresa_oras;
        this.adresa_strada = adresa_strada;
        this.adresa_numarStrada = adresa_numarStrada;
    }

    public Adresa(String adresa_tara, String adresa_oras, String adresa_strada, int adresa_numarStrada) {
        this.adresa_tara = adresa_tara;
        this.adresa_oras = adresa_oras;
        this.adresa_strada = adresa_strada;
        this.adresa_numarStrada = adresa_numarStrada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdresa_tara() {
        return adresa_tara;
    }

    public void setAdresa_tara(String adresa_tara) {
        this.adresa_tara = adresa_tara;
    }

    public String getAdresa_oras() {
        return adresa_oras;
    }

    public void setAdresa_oras(String adresa_oras) {
        this.adresa_oras = adresa_oras;
    }

    public String getAdresa_strada() {
        return adresa_strada;
    }

    public void setAdresa_strada(String adresa_strada) {
        this.adresa_strada = adresa_strada;
    }

    public int getAdresa_numarStrada() {
        return adresa_numarStrada;
    }

    public void setAdresa_numarStrada(int adresa_numarStrada) {
        this.adresa_numarStrada = adresa_numarStrada;
    }

}
