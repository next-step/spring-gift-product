package gift.exception;

public enum ErrorStatus {
    INTERNAL_SERVER_ERROR(-1, "Internal Server Error"),
    NOT_FOUND(2, "Entity Not Found"),
    VALIDATION_ERROR(-2, "Validation error"),
    REQUEST_BODY_ERROR(-3, "Request body error"),
    METHOD_NOT_ALLOWED(-4, "Method Not Allowed");

    private final int code;
    private final String defaultMessage;

    ErrorStatus(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
