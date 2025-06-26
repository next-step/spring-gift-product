package gift.dto;

public record ProductResponseDto(
    Long id,
    String name,
    long price,
    String imageUrl
) {
}