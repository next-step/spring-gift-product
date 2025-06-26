package gift.dto.response;

import gift.entity.Product;

public record ProductCreateResponseDto(Long productId,
                                       String name,
                                       Double price,
                                       String imageUrl) {

    public ProductCreateResponseDto(Product product) {
        this(product.getProductId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}