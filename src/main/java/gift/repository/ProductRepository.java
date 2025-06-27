package gift.repository;

import gift.dto.api.ProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    
    //상품 추가 api
    ProductResponseDto addProduct(Product product);
    
    List<ProductResponseDto> findAllProducts();
    
    Product findProductWithId(Long id);
    
    ProductResponseDto modifyProductWithId(Long id, Product newProduct);
    
    void deleteProductWithId(Long id);
}
