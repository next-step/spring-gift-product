package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    int saveProduct(Product product);

    List<ProductGetResponseDto> findAllProducts();

    Product findProductById(Long productId);

    int updateProductById(Long productId, String name, Double price, String imageUrl);

    int deleteProductById(Long productId);
}
