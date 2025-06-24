package gift.exception;

public class InvalidProductException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidProductException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
