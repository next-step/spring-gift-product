package gift.api.dto;

import gift.api.domain.Product;

public record ProductResponseDto(
        Long id,
        String name,
        Long price,
        String imageUrl
) {

    public static ProductResponseDto from(Product produt) {
        return new ProductResponseDto(
                produt.getId(),
                produt.getName(),
                produt.getPrice(),
                produt.getImageUrl()
        );
    }
}
