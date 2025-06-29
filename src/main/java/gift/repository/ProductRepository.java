package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void saveProduct(Product product);

    List<ProductGetResponseDto> findAllProducts();

    Optional<Product> findProductById(Long productId);

    void updateProductById(Product product);

    void deleteProductById(Long productId);
}
