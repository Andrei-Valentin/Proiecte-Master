package ro.unibuc.FinalAssignment.exception;

public class ClientAlreadyExistsException extends RuntimeException{

    public ClientAlreadyExistsException() {
        super("A client with this CNP already exists");
    }
}
