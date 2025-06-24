package gift.dto;

import gift.entity.Product;

public record ProductResponseDto(Long id, String name, Integer price, String imageUrl) {
    public ProductResponseDto(Product product) {
        this(product.id(), product.name(), product.price(), product.imageUrl());
    }
}
