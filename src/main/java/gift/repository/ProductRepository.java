package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {
    public List<ProductResponseDto> findAllProducts();
    ProductResponseDto saveProduct(Product product);
    Product findProductById(Long id);
    Product updateProduct(Long id, String name, Long price, String ImageUrl);

    void deleteProduct(Long id);
}
