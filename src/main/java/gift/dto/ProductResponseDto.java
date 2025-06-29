package gift.dto;

import gift.entity.Product;

public record ProductResponseDto(
    Long id,
    String name,
    Integer price,
    String imageUrl
) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }

    public static ProductResponseDto from(Long id, ProductRequestDto requestDto) {
        return new ProductResponseDto(
            id,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl()
        );
    }
}
