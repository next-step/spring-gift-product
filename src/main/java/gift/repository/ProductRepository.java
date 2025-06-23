package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private static final Map<Long, Product> store = new HashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public Product save(Product product) {
        product.setId(sequence.incrementAndGet());
        store.put(product.getId(), product);
        return product;
    }
}