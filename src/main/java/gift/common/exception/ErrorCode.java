package gift.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 오류가 발생했습니다. 지원팀에 문의하세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

    NULL_ERROR(HttpStatus.BAD_REQUEST, "널 포인터 예외가 발생했습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "유효하지 않은 입력값입니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "입력값 유효성 검사에 실패했습니다."),
    MALFORMED_JSON(HttpStatus.BAD_REQUEST, "잘못된 JSON 형식입니다."),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    BINDING_FAILED(HttpStatus.BAD_REQUEST, "데이터 바인딩에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }
    
    public boolean isServerError() {
        return httpStatus.is5xxServerError();
    }
    
    public boolean isClientError() {
        return httpStatus.is4xxClientError();
    }
}
