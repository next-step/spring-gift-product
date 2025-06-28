package gift.dto;

import gift.domain.Product;

public record ProductResponse(Long id, String name, int price, String imageUrl) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}