package gift.global;

import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        return buildErrorResponse(ex.getErrorCode(), ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        return buildErrorResponse(ErrorCode.UNKNOWN_ERROR, ex);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(ErrorCode errorCode,
            Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("errorCode", errorCode.name());
        errorBody.put("message", errorCode.getDescription());

        errorBody.put("trace", getStackTraceString(ex));

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorBody);
    }

    private String getStackTraceString(Exception ex) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            ex.printStackTrace(pw);
            return sw.toString();
        }
    }
}
