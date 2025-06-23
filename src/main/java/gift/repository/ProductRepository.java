package gift.repository;

import gift.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private final Map<Long, Product> storage = new HashMap<>();
    private long nextId = 1L;

    public Product save(Product product) {
        product.setId(nextId++);
        storage.put(product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

}
