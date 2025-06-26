package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long nextId = 0L;

    public Product create(Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    public Product update(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Product findById(Long id) { return products.get(id); }

    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    public Product deleteById(Long id) { return products.remove(id); }
}
