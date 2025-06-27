package gift.entity;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;

public record Product(Long id, String name, Long price, String url){
    public ProductResponseDto toProductResponseDto(){
        return new ProductResponseDto(this);
    }

    public Product(Long id, ProductUpdateRequestDto requestDto) {
        this(id, requestDto.name(), requestDto.price(), requestDto.url());
    }
}