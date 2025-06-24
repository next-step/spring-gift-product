package gift.common.exception;

/**
 * 오류가 어디서 발생했는가?를 나타냄
 * 에러필드는 오류가 발생한 구체적인 위치나 속성을 나타냄
 * 예를 들어 유효성 검사 오류나 특정 필드와 관련된 오류에서 어느 필드에서 문제가 발생했는지 명시함
 */
public enum ErrorField {
    NULL_POINTER("널 포인터"),
    INVALID_ARGUMENT("유효하지 않은 인자"),
    REQUEST_BODY("요청 본문"),
    INVALID_FORMAT("유효하지 않은 형식"),
    MISSING("누락됨"),
    INTERNAL_SERVER_ERROR("내부 서버 오류");

    private final String description;

    ErrorField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}