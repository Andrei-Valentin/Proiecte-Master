package ro.unibuc.FinalAssignment.dto.adresa;
import javax.validation.constraints.*;

public class CreateAdresaRequestDto {
    @NotBlank
    @Size(max = 50)
    private String adresa_tara;

    @NotBlank
    @Size(max = 50)
    private String adresa_oras;

    @NotBlank
    @Size(max = 50)
    private String adresa_strada;

    @NotNull
    private int adresa_numarStrada;

    public CreateAdresaRequestDto() {
    }

    public CreateAdresaRequestDto(String adresa_tara, String adresa_oras, String adresa_strada, int adresa_numarStrada) {
        this.adresa_tara = adresa_tara;
        this.adresa_oras = adresa_oras;
        this.adresa_strada = adresa_strada;
        this.adresa_numarStrada = adresa_numarStrada;
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
