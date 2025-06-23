package gift.repository;

import gift.entity.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final Map<Long, Product> productMap = new HashMap<>();
    private Long nextId = 1L;

    public Product addProduct(Product product) {
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return product;
    }

    public Product findProductById(Long id) {
        return productMap.get(id);
    }

    public Product updateProduct(Product product) {
        productMap.put(product.getId(), product);
        return product;
    }
}
