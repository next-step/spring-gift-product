package gift.global.exception;

public class BadProductRequestException extends RuntimeException {
    public BadProductRequestException(String message) {
        super(message);
    }
}
