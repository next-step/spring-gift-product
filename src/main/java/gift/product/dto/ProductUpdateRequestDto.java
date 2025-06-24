package gift.product.dto;

public record ProductUpdateRequestDto(
        String name,
        Long price,
        String imageUrl
) {
}
