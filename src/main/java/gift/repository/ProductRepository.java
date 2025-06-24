package gift.repository;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    
    //상품 추가 api
    AddProductResponseDto addProduct(AddProductRequestDto requestDto);
    
    List<FindProductResponseDto> findAllProducts();
    
    Product findProductWithDbId(Long id);
}
