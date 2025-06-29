package gift.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "G001", "요청 값이 유효하지 않습니다"),
  INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "G002", "내부 서버 오류가 발생했습니다"),
  INVALID_SORT_FIELD_ERROR(HttpStatus.BAD_REQUEST, "G003", "정렬 필드 값이 올바르지 않습니다"),
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"P001", "상품 정보를 찾을 수 없습니다");

  private final HttpStatus status;
  private final String errorCode;
  private final String errorMessage;

  ErrorCode(HttpStatus status, String errorCode, String errorMessage) {
    this.status = status;
    this.errorCode = errorCode;
    this.errorMessage =errorMessage;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
