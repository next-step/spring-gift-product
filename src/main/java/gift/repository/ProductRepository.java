package gift.repository;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product createProduct(Product newProduct);

    List<ProductResponseDto> findAllProducts();

    Optional<Product> findProductById(Long id);

    void deleteProductById(Long id);
}
