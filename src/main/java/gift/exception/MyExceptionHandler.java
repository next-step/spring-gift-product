package gift.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusError(ResponseStatusException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getReason());

        return ResponseEntity.status(e.getStatusCode()).body(errors);
    }
}
