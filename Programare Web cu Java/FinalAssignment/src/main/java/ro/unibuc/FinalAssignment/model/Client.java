package ro.unibuc.FinalAssignment.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Objects;

@Entity
@Table(name = "clienti")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String client_nume;

    @Column(name = "client_CNP", nullable = false)
    private long CNP;

    private int client_varsta;

    @Email
    @Column(name = "client_email", nullable = false)
    private String email;



    public Client() {
    }

    public Client(long id, String client_nume, long CNP, int client_varsta, String email) {
        this.id = id;
        this.client_nume = client_nume;
        this.CNP = CNP;
        this.client_varsta = client_varsta;
        this.email = email;
    }

    public Client(String client_nume, long CNP, int client_varsta, String email) {
        this.client_nume = client_nume;
        this.CNP = CNP;
        this.client_varsta = client_varsta;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClient_nume() {
        return client_nume;
    }

    public void setClient_nume(String client_nume) {
        this.client_nume = client_nume;
    }

    public long getCNP() {
        return CNP;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }

    public int getClient_varsta() {
        return client_varsta;
    }

    public void setClient_varsta(int client_varsta) {
        this.client_varsta = client_varsta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
