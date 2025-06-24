package gift.repository;

import gift.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product saveProduct(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findAllProducts();
}
