package gift.dto.response;

import gift.entity.Product;


public record ProductResponseDto(Long id, String name, Long price, String imageUrl) {
    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}


