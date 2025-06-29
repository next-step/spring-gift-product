package gift.common.exception;



public class InvalidSortFieldException extends RuntimeException {

  private final ErrorCode errorCode;

  public InvalidSortFieldException(ErrorCode errorCode) {
    super(errorCode.getErrorMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
