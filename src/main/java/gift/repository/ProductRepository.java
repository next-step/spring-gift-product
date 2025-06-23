package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }
}
