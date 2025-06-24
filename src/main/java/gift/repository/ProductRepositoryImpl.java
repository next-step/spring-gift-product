package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Optional<Product> findById(Long productId) {
        Product product = products.get(productId);

        return Optional.ofNullable(product);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void deleteById(Long productId) {
        products.remove(productId);
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }
}
