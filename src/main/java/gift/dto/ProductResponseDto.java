package gift.dto;

import gift.entity.Product;

public record ProductResponseDto(Long id, String name, Long price, String imageUrl) {
    public ProductResponseDto(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}

