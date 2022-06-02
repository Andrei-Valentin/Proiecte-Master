package ro.unibuc.FinalAssignment.exception;

public class InvalidUpdateRequestException extends RuntimeException {
    public InvalidUpdateRequestException() {
        super("Invalid update");
    }
}
