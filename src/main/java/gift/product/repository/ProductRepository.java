package gift.product.repository;

import gift.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(String name, int price, String imageUrl);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void update(Long id, String name, int price, String imageUrl);

    void deleteById(Long id);
}
