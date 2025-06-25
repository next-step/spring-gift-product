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

    public Product save(Product product) {
        Long id = sequence.incrementAndGet();
        Product savedProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        map.put(id, savedProduct);
        return savedProduct.copy();
    }

    public Optional<Product> findById(Long id) {
        if (map.containsKey(id)) {
            Product result = map.get(id);
            return Optional.of(result.copy());
        }
        return Optional.empty();
    }

    public List<Product> findAll() {
        return map.values().stream()
                .map(Product::copy)
                .toList();
    }

    public Optional<Product> update(Long id, Product product) {
        if (map.containsKey(id)) {
            Product savedProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
            map.put(id, savedProduct);
            return Optional.of(savedProduct.copy());
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
}
