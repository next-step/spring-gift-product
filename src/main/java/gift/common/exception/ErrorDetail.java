package gift.common.exception;

public record ErrorDetail(String errorType, Object rejectedValue, String reason) {
} 