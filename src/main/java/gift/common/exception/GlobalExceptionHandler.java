package gift.common.exception;

import gift.common.Response;
import gift.product.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response<Void>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Response.error(ErrorCode.INVALID_INPUT_VALUE));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Response<Void>> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Response.error(ErrorCode.INVALID_INPUT_VALUE));
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<Response<Void>> handleNotFoundException(
      ProductNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(exception.getErrorCode().getCode()) // 404
        .body(Response.error(exception.getErrorCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Response<Void>> handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Response.error(ErrorCode.INTERNAL_ERROR));
  }
}
