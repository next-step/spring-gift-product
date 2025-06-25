package gift.exception;

import org.springframework.http.HttpStatus;

public class InvalidNameException extends ApiException{
    public InvalidNameException() {
        super("Name is required", HttpStatus.BAD_REQUEST);
    }
}
