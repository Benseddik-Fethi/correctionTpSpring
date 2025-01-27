package fr.benseddik.correctiontpspring.error.exception;

import fr.benseddik.correctiontpspring.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ToString
public class UserNotFoundException extends RuntimeException implements ExceptionWithErrorResponse {

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;

    public UserNotFoundException(String message) {
        this.message = message;
        this.code = "user.not.found";
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.status = httpStatus.value();
    }
}