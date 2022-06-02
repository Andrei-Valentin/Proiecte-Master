package ro.unibuc.FinalAssignment.dto.stoc;

import javax.validation.constraints.*;

public class CreateStocRequestDto {

    @NotNull
    private long magazin_id;

    @NotNull
    private long produs_id;

    @NotNull
    private int cantitate_produs;

    public CreateStocRequestDto() {
    }

    public CreateStocRequestDto(long magazin_id, long produs_id, int cantitate_produs) {
        this.magazin_id = magazin_id;
        this.produs_id = produs_id;
        this.cantitate_produs = cantitate_produs;
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
