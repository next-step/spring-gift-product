package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductRequestDto;
import gift.dto.ModifyProductResponseDto;
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
