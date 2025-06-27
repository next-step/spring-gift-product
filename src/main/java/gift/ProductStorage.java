package gift;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductStorage {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) { return products.get(id); }

    public Product save(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        return product;
    }

    public void update(Long id, Product updated) {
        updated.setId(id);
        products.put(id, updated);
    }
    public void delete(Long id) {
        products.remove(id);
    }
}
