package ro.unibuc.FinalAssignment.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super("The client doesn't exist");
    }
}
