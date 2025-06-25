package gift.global;

import gift.dto.common.ErrorResponse;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        return buildErrorResponse(ex.getErrorCode(), ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(ErrorCode.UNKNOWN_ERROR, ex);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(ErrorCode errorCode, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                errorCode.name(),
                errorCode.getDescription(),
                getStackTraceString(ex)
        );

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    private String getStackTraceString(Exception ex) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            ex.printStackTrace(pw);
            return sw.toString();
        }
    }
}
