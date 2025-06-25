package gift.exception;

import org.springframework.http.HttpStatus;

public class InvalidImageUrlException extends ApiException{
    public InvalidImageUrlException() {
        super("Image url is required", HttpStatus.BAD_REQUEST);
    }
}
