package gift.product.exception;

import gift.global.exception.ErrorCode;


public class ProductNotFoundException extends RuntimeException {

  private final ErrorCode errorCode;

  public ProductNotFoundException(ErrorCode errorCode) {
    super(errorCode.getErrorMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
