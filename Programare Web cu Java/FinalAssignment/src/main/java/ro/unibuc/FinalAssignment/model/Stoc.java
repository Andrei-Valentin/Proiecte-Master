package ro.unibuc.FinalAssignment.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "stoc")
public class Stoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long magazin_id;

    private long produs_id;

    private int cantitate_produs;

    public Stoc() {
    }

    public Stoc(long id, long magazin_id, long produs_id, int cantitate_produs) {
        this.id = id;
        this.magazin_id = magazin_id;
        this.produs_id = produs_id;
        this.cantitate_produs = cantitate_produs;
    }

    public Stoc(long magazin_id, long produs_id, int cantitate_produs) {
        this.magazin_id = magazin_id;
        this.produs_id = produs_id;
        this.cantitate_produs = cantitate_produs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMagazin_id() {
        return magazin_id;
    }

    public void setMagazin_id(long magazin_id) {
        this.magazin_id = magazin_id;
    }

    public long getProdus_id() {
        return produs_id;
    }

    public void setProdus_id(long produs_id) {
        this.produs_id = produs_id;
    }

    public int getCantitate_produs() {
        return cantitate_produs;
    }

    public void setCantitate_produs(int cantitate_produs) {
        this.cantitate_produs = cantitate_produs;
    }
}
