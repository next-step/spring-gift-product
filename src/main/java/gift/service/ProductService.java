package gift.service;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.api.ProductResponseDto;
import java.util.List;

public interface ProductService {
    
    //상품 추가 Service
    ProductResponseDto addProduct(AddProductRequestDto requestDto);
    
    List<ProductResponseDto> findAllProducts();
    
    ProductResponseDto findProductWithId(Long id);
    
    ProductResponseDto modifyProductWithId(Long id, ModifyProductRequestDto requestDto);
    
    void deleteProductWithId(Long id);
    
    ProductResponseDto modifyProductInfoWithId(Long id, ModifyProductRequestDto requestDto);
}
