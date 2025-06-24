package gift.dto.product;

import gift.domain.Product;

public record ProductResponse(String name, Integer price) {
    public ProductResponse(Product product) {
        this(product.getName(), product.getPrice());
    }
}
