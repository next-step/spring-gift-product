// src/main/java/gift/repository/InMemoryProductRepository.java
package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/**
 * application-dev 프로필 활성화 시 주입되는 인메모리 구현체 (테스트/개발용).
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            // 신규 생성
            product.setId(seq.getAndIncrement());
        } else if (!existsById(product.getId())) {
            // 업데이트 대상이 없으면 예외
            throw new NoSuchElementException("업데이트할 상품이 없습니다: " + product.getId());
        }
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
