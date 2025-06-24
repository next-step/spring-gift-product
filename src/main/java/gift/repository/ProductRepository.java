package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Integer, Product> store = new HashMap<>();
    private Integer nextId = 1;

    public Product save(Product product) {
        Product toSave = new Product(nextId++, product.getName(), product.getPrice(), product.getImageUrl());
        store.put(toSave.getId(), toSave);
        return toSave;
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(store.values());
    }

    public Product findById(Integer id) {
        return store.get(id);
    }

    public void delete(Integer id) {
        store.remove(id);
    }
}
