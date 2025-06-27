package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {

    private final Map<Long, Product> map = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public ProductRepository() {
        saveDummyData();
    }

    public Optional<Product> save(Product product) {
        Long id = sequence.incrementAndGet();
        Product savedProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        map.put(id, savedProduct);
        return Optional.of(savedProduct);
    }

    public Optional<Product> findById(Long id) {
        if (map.containsKey(id)) {
            return Optional.of(map.get(id));
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return map.values().stream()
                .toList();
    }

    public Optional<Product> update(Long id, Product product) {
        if (map.containsKey(id)) {
            Product savedProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
            map.put(id, savedProduct);
            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        map.remove(id);
    }

    /**
     * !!test에서 사용되는 메서드!!
     * 호출 금지
     */
    public void clear() {
        map.clear();
    }

    /**
     * 테스트용 데이터 초기화 메서드
     */
    private void saveDummyData() {
        Product p1 = new Product(null, "아메리카노", 3000L, "https://americano");
        Product p2 = new Product(null, "카페라떼", 4000L, "https://cafelatte");
        Product p3 = new Product(null, "모카", 5000L, "https://moka");
        Product p4 = new Product(null, "아포가토", 4500L, "https://affogato");
        save(p1);
        save(p2);
        save(p3);
        save(p4);
    }
}
