package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product save(Product product) {
        long id = nextId++;
        Product newProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        products.put(id, newProduct);
        return newProduct;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public void update(Long id, Product updatedProduct) {
        products.put(id, updatedProduct);
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
}
