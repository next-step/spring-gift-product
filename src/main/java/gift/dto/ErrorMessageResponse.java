package gift.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessageResponse {
    private final LocalDateTime timestamp;
    private final String message;
    private final int status;
    private final String error;
    private final String path;

    public ErrorMessageResponse(HttpServletRequest request, String message, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.path = request.getRequestURI();
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getPath() { return path; }
}
