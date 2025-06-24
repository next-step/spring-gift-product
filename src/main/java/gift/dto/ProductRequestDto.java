package gift.dto;

public record ProductRequestDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {}
