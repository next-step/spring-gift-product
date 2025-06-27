package gift.exception;

import gift.dto.ErrorMessageResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageResponse> handleNoSuchElementException(
            NoSuchElementException e, HttpServletRequest request
    ) {
        var errorMessage = new ErrorMessageResponse.Builder(request, e.getMessage(), HttpStatus.NOT_FOUND)
                .stackTrace(e.getStackTrace() != null ? e.getStackTrace()[0].toString() : "")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageResponse> handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletRequest request
    ) {
        var errorMessage = new ErrorMessageResponse.Builder(request, e.getMessage(), HttpStatus.BAD_REQUEST)
                .stackTrace(e.getStackTrace() != null ? e.getStackTrace()[0].toString() : "")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DBServerException.class)
    public ResponseEntity<ErrorMessageResponse> handleDBServerException(
            DBServerException e, HttpServletRequest request
    ) {
        var errorMessage = new ErrorMessageResponse.Builder(request, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
                .stackTrace(e.getStackTrace() != null ? e.getStackTrace()[0].toString() : "")
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
