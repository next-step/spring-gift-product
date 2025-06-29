package gift.api.repository;

import gift.api.domain.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository {

    Page<Product> findAllProducts(Pageable pageable, Long categoryId);

    Optional<Product> findProductById(Long id);

    Long createProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(Long id);
}