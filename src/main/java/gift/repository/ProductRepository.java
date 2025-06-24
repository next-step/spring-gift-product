package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    private Long idCounter = 1L;

    public Product saveProduct(Product product) {
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }
}
