package gift.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    public ApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
