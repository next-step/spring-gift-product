package gift.entity;

import gift.dto.ProductResponseDto;

public record Product(Long id, String name, Long price, String url){
    public ProductResponseDto toProductResponseDto(){
        return new ProductResponseDto(this);
    }
}