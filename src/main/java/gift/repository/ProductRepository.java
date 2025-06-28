package gift.repository;

import gift.model.Product;
import java.util.List;
import java.util.Optional;

// Product 데이터 접근 로직을 정의하는 인터페이스
public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);
}
