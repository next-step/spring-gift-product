package gift.domain.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import gift.domain.product.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public Product save(Product product) {
        if (product.getId() != null) {
            products.put(product.getId(),product);
        }
        long id = sequence.incrementAndGet();
        Product newProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        products.put(id, newProduct);
        return newProduct;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
    
}
