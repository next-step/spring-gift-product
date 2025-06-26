package gift.dto;

import gift.entity.Product;

public record ProductRequestDto(String name, Long price, String imageUrl) {
    public ProductRequestDto(Product product) {
        this(product.getName(), product.getPrice(), product.getImageUrl());
    }
}
