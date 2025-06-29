package gift.api.dto;

public record ErrorResponseDto(
        int status,
        String error,
        String message,
        String path
) {

}
