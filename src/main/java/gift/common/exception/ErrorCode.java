package gift.common.exception;

/**
 * 무슨 유형의 오류가 발생했는가?를 나타냄
 * 특정 예외 상황이나 오류를 고유하게 식별함
 */
public enum ErrorCode {
    // 일반 오류
    UNEXPECTED_ERROR("예상치 못한 오류가 발생했습니다. 지원팀에 문의하세요."),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다."),

    // 입력값 검증 오류
    NULL_ERROR("널 포인터 예외가 발생했습니다."),
    INVALID_INPUT("유효하지 않은 입력값입니다."),
    VALIDATION_FAILED("입력값 유효성 검사에 실패했습니다."),
    MALFORMED_JSON("잘못된 JSON 형식입니다."),
    MISSING_PARAMETER("필수 파라미터가 누락되었습니다."),
    BINDING_FAILED("데이터 바인딩에 실패했습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}