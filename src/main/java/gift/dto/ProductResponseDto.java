package gift.dto;

import gift.entity.Product;

// 다소 생소했던 record를 사용해 보았음
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
}
