package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    // 1. 저장
    Product save(Product product);

    // 2-1. 전체 조회
    List<Product> findAll();

    // 2-2. 선택 조회
    Optional<Product> findById(Long id);

    // 3. 삭제
    void deleteById(Long id);
}
