package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    Long nextId = 1L;

    public Product save(Product product) {

        Long id = nextId++;
        product.setId(id);
        products.put(id, product);

        return product;
    }

}
