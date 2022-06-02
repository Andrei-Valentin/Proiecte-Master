package ro.unibuc.FinalAssignment.exception;

public class ClientEmailAlreadyExistsException extends RuntimeException{

    public ClientEmailAlreadyExistsException() {
        super("A client with this email already exists");
    }
}
