package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product update(Long id, String name, int price, String imageUrl);

    boolean delete(Long id);
}
