package gift.repository;

import gift.domain.Product;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private static final AtomicLong sequence = new AtomicLong(0);
    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        Long id = generateId();
        Product productWithId = Product.withId(
                id,
                product.name(),
                product.price(),
                product.imageUrl()
        );

        products.put(id, productWithId);
    }

    @Override
    public void update(Long id, Product updatedProduct) {
        products.computeIfPresent(id, (key, existingProduct) -> existingProduct.update(
                updatedProduct.name(),
                updatedProduct.price(),
                updatedProduct.imageUrl()
        ));
        
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품 ID입니다: " + id);
        }
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    private Long generateId() {
        return sequence.incrementAndGet();
    }
}
