// src/main/java/gift/repository/ProductRepository.java
package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * 순수 자바 인터페이스로만 정의된 리포지토리. JPA가 아닌, 직접 구현체를 주입해서 사용할 수 있습니다.
 */
public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    boolean existsById(Long id);

    Product save(Product product);

    void deleteById(Long id);
}
