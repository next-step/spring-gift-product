package gift.repository;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    long createProduct(ProductRequestDto requestDto);
    List<Product> findAllProducts();
    Product findProductById(Long id);
    void updateProduct(Long id, ProductRequestDto requestDto);
    void deleteProduct(Long id);
}
