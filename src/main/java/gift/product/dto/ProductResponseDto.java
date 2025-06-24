package gift.product.dto;


public record ProductResponseDto(
        Long id,
        String name,
        Long price,
        String imageUrl
) {
}
