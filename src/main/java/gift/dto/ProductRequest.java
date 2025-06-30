package gift.dto;

import gift.entity.Product;

public record ProductRequest(
        String name,
        Integer price,
        String imageUrl
) {
    public Product toEntity() {
        return new Product(name, price, imageUrl);
    }
}
