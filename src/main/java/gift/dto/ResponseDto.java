package gift.dto;

import gift.entity.Product;

public record ResponseDto(
        Long id,
        String name,
        Double price,
        String imageUrl
){
    public ResponseDto(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }
}
