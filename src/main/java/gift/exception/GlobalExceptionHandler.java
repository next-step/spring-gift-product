package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// 예외 관리 핸들러
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFound(ProductNotFoundException e) {
        return e.getMessage();
    }
}
