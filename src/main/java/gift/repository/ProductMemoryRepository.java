package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductMemoryRepository implements ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        store.put(id, product);
        return product;
    }
}

