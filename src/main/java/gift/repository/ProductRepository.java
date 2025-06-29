package gift.repository;

import gift.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void register(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void update(Long id, Product product);

    void delete(Long id);

    List<Product> searchByName(String keyword);
}
