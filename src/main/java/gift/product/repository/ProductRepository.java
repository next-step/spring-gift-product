package gift.product.repository;

import gift.domain.Product;
import gift.product.dto.ProductCreateRequest;

import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    String addProduct(ProductCreateRequest dto);

    List<Product> findAll();

    Optional<Product> findById(String id);
}
