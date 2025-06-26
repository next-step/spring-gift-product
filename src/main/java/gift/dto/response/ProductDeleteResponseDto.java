package gift.dto.response;

import gift.entity.Product;

public record ProductDeleteResponseDto(Long productId,
                                       String name,
                                       Double price,
                                       String imageUrl) {

    public ProductDeleteResponseDto(Product product) {
        this(product.getProductId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}