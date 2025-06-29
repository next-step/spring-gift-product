package gift.global.exception.dto;

import gift.global.exception.ErrorCode;

public record ErrorResponse(String errorCode, String errorMessage) {

  public static ErrorResponse from(ErrorCode errorCode) {
    return new ErrorResponse(
        errorCode.getErrorCode(),
        errorCode.getErrorMessage()
    );
  }

}