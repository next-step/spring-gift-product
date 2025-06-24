package gift.product.repository;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;
import gift.product.dto.ProductUpdateRequest;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    String save(ProductCreateRequest dto);

    List<Product> findAll();

    Optional<Product> findById(String id);

    void deleteById(String id);

    void update(String id, ProductUpdateRequest dto);
}
