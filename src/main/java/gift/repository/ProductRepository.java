package gift.repository;

import gift.domain.Product;
import gift.dto.CreateProductRequest;

import java.util.Optional;


public interface ProductRepository {

    Product save(CreateProductRequest request);

    Optional<Product> findById(Long id);
}
