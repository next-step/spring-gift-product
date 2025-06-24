package gift.dto;

import gift.entity.Product;

public record ProductRequestDto(String name, Integer price, String imageUrl) {
    public Product toEntity(Long id) {
        return new Product(id, name, price, imageUrl);
    }
}

