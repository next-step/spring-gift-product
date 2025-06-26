package gift.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record ErrorMessageResponse (
    LocalDateTime timestamp,
    String message,
    int status,
    String error,
    String path,
    String stackTrace
) {

    public static class Builder {
        HttpServletRequest request;
        String message;
        HttpStatus status;
        String stackTrace = "";

        public Builder(HttpServletRequest request, String message, HttpStatus status) {
            this.request = request;
            this.message = message;
            this.status = status;
        }

        public Builder stackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public ErrorMessageResponse build() {
            return new ErrorMessageResponse(
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()),
                message,
                status.value(),
                status.getReasonPhrase(),
                request.getRequestURI(),
                stackTrace
            );
        }
    }
}