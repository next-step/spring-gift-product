package gift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = e.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400 Bad Request

        if (errorMessage.contains("상품을 찾을 수 없습니다")) {
            status = HttpStatus.NOT_FOUND; // 404 Not Found
        } else if (errorMessage.contains("이미 존재하는 상품 ID")) {
            status = HttpStatus.CONFLICT; // 409 Conflict
        } else if (errorMessage.contains("유효하지 않습니다")) {
            status = HttpStatus.BAD_REQUEST; // 400 Bad Request
        } else if (errorMessage.contains("ID가 일치하지 않습니다")) {
            status = HttpStatus.BAD_REQUEST; // 400 Bad Request
        }

        System.err.println(
                "[API Exception] 처리: " + errorMessage + " -> HTTP Status: " + status.value());
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        System.err.println("[API Exception] 예상치 못한 서버 오류: " + e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>("내부 서버 오류가 발생했습니다.",
                HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
    }
}
