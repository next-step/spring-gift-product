package gift.common.exception;

import gift.common.ErrorResponse;
import gift.product.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException() {
    return createErrorResponse(ErrorCode.INVALID_ARGUMENT_ERROR);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException() {
    return createErrorResponse(ErrorCode.INVALID_ARGUMENT_ERROR);
  }

  @ExceptionHandler(InvalidSortFieldException.class)
  public ResponseEntity<ErrorResponse> handleSortFieldException(InvalidSortFieldException exception) {
    return createErrorResponse(exception.getErrorCode());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(
      ProductNotFoundException exception) {
    return createErrorResponse(exception.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException() {
    return createErrorResponse(ErrorCode.INTERNAL_ERROR);
  }

  private static ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode){
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ErrorResponse.from(errorCode));
  }
}
