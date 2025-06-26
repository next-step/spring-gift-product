package gift.domain.product.repository;

import java.util.List;

import gift.domain.product.model.Product;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);

}
