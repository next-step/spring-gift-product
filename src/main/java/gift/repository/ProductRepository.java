package gift.repository;

import gift.domain.Product;
import gift.dto.ProductRequest;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long productId);

    void save(Product product);

    void update(ProductRequest request);

    void deleteById(Long productId);

    List<Product> findAll();

}
