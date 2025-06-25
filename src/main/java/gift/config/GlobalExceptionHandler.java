package gift.config;

import gift.dto.response.ErrorResponseDto;
import gift.exception.EntityNotFoundException;
import gift.exception.RequestValidateFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RequestValidateFailException.class)
    public ResponseEntity<ErrorResponseDto> handleRequestValidateFail(RequestValidateFailException e) {
        log.warn("RequestValidateFail: {}", e.getMessage());
        var response = new ErrorResponseDto(e.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException e) {
        log.warn("EntityNotFoundException: {}", e.getMessage());
        return ResponseEntity.notFound().build(); //404
    }
}
