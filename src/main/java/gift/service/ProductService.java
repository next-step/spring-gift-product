package gift.service;

import gift.domain.Product;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;


import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private long idSequence = 1;

    public List<Product> findAll() {
        return new ArrayList<>(productStore.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productStore.get(id));
    }

    public Product save(Product product) {
        product.setId(idSequence++);
        productStore.put(product.getId(), product);
        return product;
    }

    public boolean update(Long id, Product updated) {
        if (!productStore.containsKey(id)) return false;
        updated.setId(id);
        productStore.put(id, updated);
        return true;
    }

    public boolean delete(Long id) {
        return productStore.remove(id) != null;
    }

}
