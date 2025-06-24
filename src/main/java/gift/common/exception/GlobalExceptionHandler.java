package gift.common.exception;

import gift.item.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleItemNotFoundException(ItemNotFoundException e) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
            e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
