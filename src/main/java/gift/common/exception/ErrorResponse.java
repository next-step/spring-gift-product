package gift.common.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, String message) {
}
