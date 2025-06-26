package gift.exception;

import gift.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusError(ResponseStatusException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getReason());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errors);

        return new ResponseEntity<>(errorResponseDto, e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(errors);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
