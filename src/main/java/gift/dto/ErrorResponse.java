package gift.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String message;
    private final int status;
    private final LocalDateTime timeStamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
