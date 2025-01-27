package fr.benseddik.correctiontpspring.error.exception;

import fr.benseddik.correctiontpspring.error.record.ExceptionWithErrorResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ToString
public class BorrowingLimitExceededException extends RuntimeException implements ExceptionWithErrorResponse {
    private final String message;
    private final String code;
    private final HttpStatus httpStatus;
    private final int status;

    public BorrowingLimitExceededException(String message) {
        this.message = message;
        this.code = "borrowing.limit.exceeded";
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.status = httpStatus.value();
    }
}
