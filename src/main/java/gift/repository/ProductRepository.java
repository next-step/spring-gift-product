package gift.repository;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.UpdateProductRequest;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    Product save(CreateProductRequest request);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product update(Long id, UpdateProductRequest request);

    void delete(Long id);
}
