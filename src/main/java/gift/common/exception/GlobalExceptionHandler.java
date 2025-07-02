package gift.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ProblemDetail> handleNpe(NullPointerException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.NULL_ERROR;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        body.setProperty("code", errorCode.name());
        body.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ProblemDetail> handleNumberFormat(NumberFormatException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), "올바르지 않은 숫자 형식입니다.");
        body.setProperty("code", errorCode.name());
        body.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetail> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), String.format("'%s' 파라미터 값이 올바르지 않습니다.", ex.getName()));
        body.setProperty("code", errorCode.name());
        body.setProperty("parameter", ex.getName());
        body.setProperty("rejectedValue", ex.getValue());
        body.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex, HttpServletRequest req) {
        ErrorCode errorCode = ErrorCode.UNEXPECTED_ERROR;
        ProblemDetail body = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        body.setProperty("code", errorCode.name());
        body.setProperty("path", req.getRequestURI());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);
    }
}
