package gift.entity;

import gift.dto.ProductRequestDto;

public record Product(Long id, String name, Long price, String imageUrl) {
    public Product(ProductRequestDto requestDto){
        this(null, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }
}
