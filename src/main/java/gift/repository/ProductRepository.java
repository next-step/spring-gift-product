package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Product save(Product product) {
        product.setId(nextId++);
        store.put(product.getId(), product);
        return product;
    }

    public void update(Long id, Product updatedProduct) {
        Product existing = store.get(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImageUrl(updatedProduct.getImageUrl());
        }
    }

    public Product findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }


}
