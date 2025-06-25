package gift.dto;

import gift.entity.Product;

public record ResponseDto(Long productId,
                          String name,
                          Double price,
                          String imageUrl) {

    public ResponseDto(Product product) {
        this(product.getProductId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}