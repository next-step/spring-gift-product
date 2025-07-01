package gift.repository;

import gift.entity.Product;
import gift.entity.ProductRecord;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product saveProduct(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findAllProducts();

    void updateProduct(Product product);

    void deleteProductById(Long id);

    void deleteAllProducts();
}
