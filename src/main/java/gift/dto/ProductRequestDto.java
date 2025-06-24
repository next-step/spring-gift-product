package gift.dto;

// 다소 생소했던 record를 사용해 보았음
public record ProductRequestDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {}
