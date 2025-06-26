package gift.common.exception;

public class ErrorResponseDto {

    private final int httpStatusCode;
    private final String message;


    public ErrorResponseDto(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMessage() {
        return message;
    }
}
