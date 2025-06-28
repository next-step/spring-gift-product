package gift.domain.product.repository;

import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.domain.product.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Page<Product> find(Pageable pageable);

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);

}
