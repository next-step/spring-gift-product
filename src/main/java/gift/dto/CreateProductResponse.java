package gift.dto;

import gift.domain.Product;

public record CreateProductResponse(Long id, String name, Integer price, String imageUrl) {

    public static CreateProductResponse of(Product product) {
        return new CreateProductResponse(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}