package ro.unibuc.FinalAssignment.dto.magazin;

import javax.validation.constraints.*;

public class CreateMagazinRequestDto {

    @NotNull
    private String magazin_denumire;

    @NotNull
    private long adresa;

    public CreateMagazinRequestDto() {
    }

    public CreateMagazinRequestDto(String magazin_denumire, long adresa) {
        this.magazin_denumire = magazin_denumire;
        this.adresa = adresa;
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
