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

    public Product updateProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Optional<Product> putProductById(Long id, String name, Integer price, String imageUrl) {
        return Optional.ofNullable(
                products.computeIfPresent(
                        id, (key, product) -> {
                            product.setName(name);
                            product.setPrice(price);
                            product.setImageUrl(imageUrl);
                            return product;
                        })
        );
    }

    public void deleteProductById(Long id) {
        products.remove(id);
    }
}
