package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    ProductResponseDto createProduct(Product product);
    List<ProductResponseDto> searchAllProducts();
    Optional<Product> searchProductById(Long id);
    Product updateProduct(Long id, String name, Integer price, String imageUrl);
    void deleteProduct(Long id);
}
