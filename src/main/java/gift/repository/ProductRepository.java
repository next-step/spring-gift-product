package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    Long saveProduct(String name, Integer price, String imageUrl);
    Product findProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product);
    List<Product> findAllProducts();
}
