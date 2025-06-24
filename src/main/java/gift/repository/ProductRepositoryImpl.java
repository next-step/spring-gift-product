package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        product.setId(productId);
        products.put(productId, product);
        return product;
    }
}
