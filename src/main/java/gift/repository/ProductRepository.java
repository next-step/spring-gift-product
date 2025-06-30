package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private static final Map<Long, Product> products;
    private static Long autoIncrementId;

    static {
        products = new HashMap<>();
        autoIncrementId = 0L;
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(++autoIncrementId);
        }

        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public List<Product> findAll() {
        return products.values()
            .stream()
            .toList();
    }
}
