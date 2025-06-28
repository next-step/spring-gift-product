package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product not found");
        }

        if (product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        if (product.getId() == null) {
            product = new Product(
                idGenerator.getAndIncrement(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
            );
        }

        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

}
