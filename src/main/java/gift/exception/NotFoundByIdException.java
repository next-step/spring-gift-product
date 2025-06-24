package gift.exception;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(String message) {
        super(message);
    }
}
