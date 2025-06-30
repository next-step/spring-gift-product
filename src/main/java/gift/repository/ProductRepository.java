package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Product update(Product product);

    Optional<Product> findById(Long id);

    void delete(Long id);

    List<Product> findAll();

}
