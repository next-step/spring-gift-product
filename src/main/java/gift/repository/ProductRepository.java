package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public Product save(String name, int price, String imageUrl) {
        Long newId = sequence.incrementAndGet();
        Product product = new Product(newId, name, price, imageUrl);
        products.put(newId, product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public boolean deleteById(Long id) {
        return products.remove(id) != null;
    }

    public Optional<Product> update(Long id, String name, int price, String imageUrl) {
        Product product = products.get(id);
        if (product == null) {
            return Optional.empty();
        }
        product.update(name, price, imageUrl);
        return Optional.of(product);
    }
}
