package gift.dto.response;

import gift.entity.Product;

public record ProductGetResponseDto(Long productId,
                                    String name,
                                    Double price,
                                    String imageUrl) {

    public ProductGetResponseDto(Product product) {
        this(product.getProductId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}