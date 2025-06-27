package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    List<ProductGetResponseDto> findAllProducts();

    Product findProductByProductId(Long productId);

    int deleteProductByProductId(Long productId);
}
