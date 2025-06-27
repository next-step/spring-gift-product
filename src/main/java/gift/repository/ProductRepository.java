package gift.repository;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.List;

public interface ProductRepository {

    Product createProduct(Product newProduct);

    List<ProductResponseDto> findAllProducts();

    Product findProductById(Long id);

    void deleteProductById(Long id);
}
