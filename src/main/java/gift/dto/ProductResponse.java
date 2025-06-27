package gift.dto;

import gift.entity.Product;

public record ProductResponse(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
    public static ProductResponse from(Product product) {
        return product.toResponse();
    }
}
