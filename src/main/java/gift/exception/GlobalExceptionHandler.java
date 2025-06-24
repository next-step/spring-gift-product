package gift.exception;

import gift.dto.ResErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResErrorMessageDto> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ResErrorMessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResErrorMessageDto> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ResErrorMessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
