package gift.exception;

import gift.dto.ResErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResErrorMessage> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(new ResErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
