package gift.dto.product;

import gift.domain.Product;

public record ProductResponse(String name, Integer price) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getName(), product.getPrice());
    }
}
