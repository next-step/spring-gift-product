package gift.repository;

import gift.entity.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
    Product saveProduct(Product product);
    Product findProductById(Long id);
    Product updateProduct(Long id, String name, Long price, String ImageUrl);
    void deleteProduct(Long id);
}
