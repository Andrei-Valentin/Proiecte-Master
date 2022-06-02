package ro.unibuc.FinalAssignment.dto.clienti;
import javax.persistence.Column;
import javax.validation.constraints.*;


public class CreateClientRequestDto {

    @NotBlank
    @Size(max = 50)
    private String client_nume;

    @NotNull
    private long client_CNP;

    @NotNull
    private int client_varsta;

    @NotBlank
    private String client_email;

    public CreateClientRequestDto() {
    }

    public CreateClientRequestDto(String client_nume, long client_CNP, int client_varsta, String client_email) {
        this.client_nume = client_nume;
        this.client_CNP = client_CNP;
        this.client_varsta = client_varsta;
        this.client_email = client_email;
    }

    public String getClient_nume() {
        return client_nume;
    }

    public void setClient_nume(String client_nume) {
        this.client_nume = client_nume;
    }

    public long getClient_CNP() {
        return client_CNP;
    }

    public void setClient_CNP(long client_CNP) {
        this.client_CNP = client_CNP;
    }

    public int getClient_varsta() {
        return client_varsta;
    }

    public void setClient_varsta(int client_varsta) {
        this.client_varsta = client_varsta;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }
}
