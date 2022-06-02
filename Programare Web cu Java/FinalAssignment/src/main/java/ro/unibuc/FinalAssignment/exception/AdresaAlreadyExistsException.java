package ro.unibuc.FinalAssignment.exception;

public class AdresaAlreadyExistsException extends RuntimeException{

    public AdresaAlreadyExistsException() {
        super("Adress already exists");
    }
}
