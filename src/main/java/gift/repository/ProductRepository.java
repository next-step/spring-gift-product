package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<ProductResponseDto> findAllProducts();
    Product findProductByIdElseThrow(Long id);
    ProductResponseDto saveProduct(String name, Long price, String imageUrl);
    Product updateProduct(Long id, String name, Long price, String imageUrl);

}
