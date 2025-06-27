package gift.common.dto;

public record ErrorResponseDto(
        int statusCode,
        String message
) {

}
