package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private static final Map<Long, Product> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public Product save(Product product) {
        Long newId = sequence.incrementAndGet();
        product.assignId(newId);
        store.put(newId, product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void update(Product product) {
        store.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Long id) {
        store.remove(id);
    }
}