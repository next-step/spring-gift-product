package gift.common.exception;

import java.util.List;

public record ErrorResponse(String errorCode, List<ErrorDetail> errors) {
}