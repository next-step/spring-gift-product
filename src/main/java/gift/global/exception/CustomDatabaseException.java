package gift.global.exception;

public class CustomDatabaseException extends RuntimeException {
  public CustomDatabaseException(String message) {
    super(message);
  }
}
