package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>(); // Thread-safe 를 위해 ConcurrentHashMap 사용
    private Long sequence = 1L;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(sequence++);
        }
        store.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
