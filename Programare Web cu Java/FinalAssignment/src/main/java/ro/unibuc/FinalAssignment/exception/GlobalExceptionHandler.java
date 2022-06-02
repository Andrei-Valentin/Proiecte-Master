package ro.unibuc.FinalAssignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AdresaAlreadyExistsException.class})
    public ResponseEntity handle(AdresaAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
   }

    @ExceptionHandler({ClientAlreadyExistsException.class})
    public ResponseEntity handle(ClientAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ClientEmailAlreadyExistsException.class})
    public ResponseEntity handle(ClientEmailAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({InvalidUpdateRequestException.class})
    public ResponseEntity handle(InvalidUpdateRequestException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ClientNotFoundException.class})
    public ResponseEntity handle(ClientNotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
