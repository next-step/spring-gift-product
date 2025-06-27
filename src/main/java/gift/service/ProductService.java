package gift.service;

import gift.model.Product;
import java.util.List;
import java.util.Optional;

// Product 관련 비즈니스 로직을 정의하는 인터페이스
public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
