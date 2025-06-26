package gift.api.repository;

import gift.api.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository {

    Page<Product> findAllProducts(Pageable pageable, Long categoryId);

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
