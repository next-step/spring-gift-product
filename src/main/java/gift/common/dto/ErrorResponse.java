package gift.common.dto;

import gift.common.exception.ErrorCode;

public record ErrorResponse(String errorCode, String errorMessage) {

  public static ErrorResponse from(ErrorCode errorCode) {
    return new ErrorResponse(
        errorCode.getErrorCode(),
        errorCode.getErrorMessage()
    );
  }

}