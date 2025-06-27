package gift.repository;

import gift.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Long saveProduct(String name, Integer price, String imageUrl);
    Optional<Product> findProductById(Long id);
    void deleteProductById(Long id);
    boolean updateProduct(Product product);
    List<Product> findAllProducts();
}
