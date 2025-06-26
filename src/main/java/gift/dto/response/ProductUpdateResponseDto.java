package gift.dto.response;

import gift.entity.Product;

public record ProductUpdateResponseDto(Long productId,
                                       String name,
                                       Double price,
                                       String imageUrl) {

    public ProductUpdateResponseDto(Product product) {
        this(product.getProductId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}