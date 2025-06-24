package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import java.util.List;

public interface ProductService {
    
    //상품 추가 Service
    AddProductResponseDto addProduct(AddProductRequestDto requestDto);
    
    List<FindProductResponseDto> findAllProducts();
    
    FindProductResponseDto findProductWithDbId(Long id);
}
