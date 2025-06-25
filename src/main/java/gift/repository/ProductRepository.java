package gift.repository;

import gift.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product); //상품 등록
    List<Product> findAll(); //전체 조회
    Optional<Product> findById(Long id);  // 단일 조회
    void deleteById(Long id);
    boolean existsById(Long id);
}

