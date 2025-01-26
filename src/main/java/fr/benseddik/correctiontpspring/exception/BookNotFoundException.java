package fr.benseddik.correctiontpspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super("Livre avec UUID " + message + " non trouvé");
    }

    public BookNotFoundException() {
        super("Book not found");
    }
}
