package gift.repository;

import gift.domain.Product;
import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
