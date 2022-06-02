package ro.unibuc.FinalAssignment.dto.clienti;

import javax.validation.constraints.NotNull;

public class UpdateClientRequestDto extends CreateClientRequestDto {

    @NotNull
    private long id;

    public UpdateClientRequestDto(String client_nume, long client_CNP, int client_varsta, String client_email, long id){
        super(client_nume, client_CNP, client_varsta, client_email);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
