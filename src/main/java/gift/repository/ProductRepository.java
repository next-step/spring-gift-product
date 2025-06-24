package gift.repository;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;

public interface ProductRepository {
    
    //상품 추가 api
    AddProductResponseDto addProduct(AddProductRequestDto requestDto);
}
