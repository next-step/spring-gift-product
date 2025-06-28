package gift.exception;

public class DBServerException extends RuntimeException {
    public DBServerException(String message) {
        super(message);
    }
    public DBServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
