package gift.domain.product.repository;

import java.util.List;

import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.domain.product.model.Product;

public interface ProductRepository {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);

}
