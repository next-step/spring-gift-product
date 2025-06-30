package gift.dto;

import gift.entity.Product;

public record ProductRequest(
        Long id,
        String name,
        Integer price,
        String imageUrl
) {
    public Product toEntity() {
        return new Product(id, name, price, imageUrl);
    }
}
