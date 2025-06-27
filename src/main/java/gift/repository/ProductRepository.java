package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    int saveProduct(Product product);

    List<ProductGetResponseDto> findAllProducts();

    Optional<Product> findProductById(Long productId);

    int updateProductById(Long productId, String name, Double price, String imageUrl);

    int deleteProductById(Long productId);
}
