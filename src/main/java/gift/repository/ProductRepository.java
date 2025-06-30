package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public Product saveNewProduct(Product product) {
        product.setId(counter.incrementAndGet());
        products.put(product.getId(), product);
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> getProductList() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> updateProduct(Product product) {
        return Optional.ofNullable(
                products.computeIfPresent(
                        product.getId(),
                        (key, existing) -> product
                )
        );
    }

    public Optional<Product> deleteProductById(Long id) {
        return Optional.ofNullable(products.remove(id));
    }
}
