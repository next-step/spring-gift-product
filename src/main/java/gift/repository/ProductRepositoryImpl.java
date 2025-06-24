package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product addProduct(String name, Long price, String url) {
        Long newId = products.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
        Product product = new Product(newId, name, price, url);
        products.put(newId, product);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        Product product = products.get(id);
        return product;
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String url) {
        Product product = new Product (id, name, price, url);
        products.put(id, product);
        return product;
    }
}
