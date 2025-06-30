package gift.common.exception;

import gift.common.code.CustomResponseCode;
import gift.common.dto.CustomResponseBody;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseBody<Void>> handleCustomException(CustomException e) {
        CustomResponseCode errorCode = e.getErrorCode();

        return ResponseEntity
            .status(errorCode.getHttpStatus())
            .body(new CustomResponseBody<>(errorCode.getCode(), errorCode.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponseBody<Void>> handleValidationException(
        MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return ResponseEntity
            .status(CustomResponseCode.VALIDATION_FAILED.getHttpStatus())
            .body(new CustomResponseBody<>(
                CustomResponseCode.VALIDATION_FAILED.getCode(),
                errors,
                null));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CustomResponseBody<Void>> handleDBException(DataAccessException e) {
        return ResponseEntity.status(CustomResponseCode.DB_ERROR.getCode())
            .body(new CustomResponseBody<>(
                CustomResponseCode.DB_ERROR.getCode(),
                CustomResponseCode.DB_ERROR.getMessage(),
                null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseBody<Void>> handleGeneralException(Exception e) {
        return ResponseEntity
            .status(CustomResponseCode.INTERNAL_ERROR.getHttpStatus())
            .body(new CustomResponseBody<>(
                CustomResponseCode.INTERNAL_ERROR.getCode(),
                CustomResponseCode.INTERNAL_ERROR.getMessage(),
                null));
    }
}
