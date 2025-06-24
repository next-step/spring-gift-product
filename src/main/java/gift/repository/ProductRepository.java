package gift.repository;

import gift.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Product saveProduct(Product product);
    Optional<Product> findProductById(Long id);
}
