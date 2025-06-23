package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final Map<Long, Product> store = new HashMap<>();
    private long sequence = 1L;

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    public Product save(Product product) {
        product.setId(sequence++);
        store.put(product.getId(), product);
        return product;
    }
    public void update(Long id, Product updated) {
        store.put(id, updated);
    }
}
