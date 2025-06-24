package gift.common.code;

import org.springframework.http.HttpStatus;

public enum CustomResponseCode {

    CREATED(201, "생성 성공", HttpStatus.CREATED),
    RETRIEVED(200, "조회 성공", HttpStatus.OK),
    UPDATED(200, "수정 성공", HttpStatus.OK),
    DELETED(204, "삭제 성공", HttpStatus.NO_CONTENT),
    LIST_RETRIEVED(200, "목록 조회 성공", HttpStatus.OK),

    VALIDATION_FAILED(400, "요청 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(500, "서버 내부 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    CustomResponseCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
