package gift.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String messgae;
    private final int status;
    private final LocalDateTime timeStamp;

    public ErrorResponse(String messgae, int status) {
        this.messgae = messgae;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

    public String getMessgae() {
        return messgae;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
