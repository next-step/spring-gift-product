package gift.api.dto;

public record ProductResponseDto(
        Long id,
        String name,
        Long price,
        String imageUrl
) {

}
