package gift.service;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.AddProductResponseDto;
import gift.dto.api.FindProductResponseDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.api.ModifyProductResponseDto;
import java.util.List;

public interface ProductService {
    
    //상품 추가 Service
    AddProductResponseDto addProduct(AddProductRequestDto requestDto);
    
    List<FindProductResponseDto> findAllProducts();
    
    FindProductResponseDto findProductWithId(Long id);
    
    ModifyProductResponseDto modifyProductWithId(Long id, ModifyProductRequestDto requestDto);
    
    void deleteProductWithId(Long id);
    
    ModifyProductResponseDto modifyProductInfoWithId(Long id, ModifyProductRequestDto requestDto);
}
