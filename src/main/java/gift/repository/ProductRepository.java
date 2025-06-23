package gift.repository;

import gift.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product update(Long id, Product product);
    void deleteById(Long id);
}
