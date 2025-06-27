package gift.repository;

import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    
    //상품 추가 api
    AddProductResponseDto addProduct(Product product);
    
    List<FindProductResponseDto> findAllProducts();
    
    Product findProductWithId(Long id);
    
    ModifyProductResponseDto modifyProductWithId(Long id, Product newProduct);
    
    void deleteProductWithId(Long id);
}
