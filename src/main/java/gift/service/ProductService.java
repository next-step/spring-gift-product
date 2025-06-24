package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;

public interface ProductService {
    
    //상품 추가 Service
    AddProductResponseDto addProduct(AddProductRequestDto requestDto);
}
