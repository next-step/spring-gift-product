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
    String path
) {

    public static class Builder {
        HttpServletRequest request;
        String message;
        HttpStatus status;

        public Builder(HttpServletRequest request) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ErrorMessageResponse build() {
            return new ErrorMessageResponse(
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()),
                message,
                status.value(),
                status.getReasonPhrase(),
                request.getRequestURI()
            );
        }
    }
}