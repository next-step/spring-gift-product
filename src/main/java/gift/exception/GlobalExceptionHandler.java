package gift.exception;

import gift.dto.ErrorMessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageResponse> handleNoSuchElementException(
            NoSuchElementException e, HttpServletRequest request
    ) {

        return new ResponseEntity<>(new ErrorMessageResponse(
                request, e.getMessage(), HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request
    ) {
        return new ResponseEntity<>(new ErrorMessageResponse(
                request, e.getMessage(), HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}
