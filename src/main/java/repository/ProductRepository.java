package repository;

import gift.entity.Product;
import java.util.Optional;

public interface ProductRepository {

    // 1. 저장
    Product save(Product product);

    // 2. 조회
    Optional<Product> findById(Long id);

    // 3. 삭제
    void deleteById(Long id);
}
