package gift.product.dto;

import gift.product.Product;

public record ProductResponse(Long id, String name, Long price, String imageUrl) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}
