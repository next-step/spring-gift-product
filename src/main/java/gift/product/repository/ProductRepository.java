package gift.product.repository;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository {

    UUID save(Product product);

    List<Product> findAll();

    Optional<Product> findById(UUID id);

    void deleteById(UUID id);

    void update(Product product);
}
