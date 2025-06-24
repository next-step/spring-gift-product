package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private static final Map<Long, Product> products;

    static {
        products = new HashMap<>();
    }

    public Product save(Product product) {
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    public Product findById(Long id) {
        return products.get(id);
    }
}
