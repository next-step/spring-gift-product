package gift.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        return createErrorResponse(
                ErrorCode.NULL_ERROR,
                ErrorField.NULL_POINTER.name(),
                ex.getClass().getSimpleName(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return createErrorResponse(
                ErrorCode.INVALID_INPUT,
                ErrorField.INVALID_ARGUMENT.name(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ErrorDetail> errorDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDetail(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.VALIDATION_FAILED.name(), errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return createErrorResponse(
                ErrorCode.MALFORMED_JSON,
                ErrorField.REQUEST_BODY.name(),
                ErrorField.INVALID_FORMAT.name(),
                "유효하지 않은 요청 본문 형식입니다",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        return createErrorResponse(
                ErrorCode.MISSING_PARAMETER,
                paramName,
                ErrorField.MISSING.name(),
                "필수 파라미터가 누락되었습니다",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {

        List<ErrorDetail> errorDetails = ex.getFieldErrors().stream()
                .map(error -> new ErrorDetail(
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BINDING_FAILED.name(), errorDetails);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        return createErrorResponse(
                ErrorCode.UNEXPECTED_ERROR,
                ErrorField.INTERNAL_SERVER_ERROR.name(),
                ex.getClass().getSimpleName(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode, String field, Object rejectedValue, HttpStatus status) {
        return createErrorResponse(errorCode, field, rejectedValue, errorCode.getMessage(), status);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(ErrorCode errorCode, String field, Object rejectedValue, String message, HttpStatus status) {
        ErrorDetail errorDetail = new ErrorDetail(field, rejectedValue, message);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.name(), List.of(errorDetail));
        return new ResponseEntity<>(errorResponse, status);
    }
}