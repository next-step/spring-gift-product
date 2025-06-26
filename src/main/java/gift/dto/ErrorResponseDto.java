package gift.dto;

import java.util.Map;

public record ErrorResponseDto(
        Map<String, String> errors
) {
}
